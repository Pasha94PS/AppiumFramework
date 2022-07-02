package com.griddynamics.ppetrenko.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.helpers.DeviceHelper.*;

public class EmulatorHelper {
    private static final String sdkPath = System.getenv("ANDROID_HOME") + File.separator;
    private static final String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private static final String emulatorPath = sdkPath +  "emulator" + File.separator + "emulator";
    private static boolean isEmulatorStarted = false;

    /**
     * Starts an emulator for the provided AVD name
     *
     * @param nameOfAVD
     */
    public static void launchEmulator(String nameOfAVD) {
        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
        String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
        try {
            Runtime.getRuntime().exec(aCommand);
            waitForEmulatorToBeReady(3, TimeUnit.MINUTES);
            System.out.println("Emulator launched successfully!");
            isEmulatorStarted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the emulator to be ready
     */
    private static void waitForEmulatorToBeReady(long timeout, TimeUnit unit) throws TimeoutException {
        Process process;
        BufferedReader inputStream;
        String terminalOutput;
        long remainingNanos = unit.toNanos(timeout);
        long deadline = System.nanoTime() + remainingNanos;

        try {
            // wait till dev.bootcomplete returns "1"
            String[]commandBootComplete = new String[]{adbPath, "-s", getDeviceToRun().getDeviceUdid(), "shell", "getprop", "dev.bootcomplete"};
            do {
                process = Runtime.getRuntime().exec(commandBootComplete);
                inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                process.waitFor(1, TimeUnit.SECONDS);
                terminalOutput = inputStream.readLine();
                remainingNanos = deadline - System.nanoTime();
            } while ((terminalOutput == null || !terminalOutput.equals("1")) && remainingNanos > 0);

            // wait till the property returns 'stopped'
            String[] commandBootAnim = new String[]{adbPath, "-s", getDeviceToRun().getDeviceUdid(), "shell", "getprop", "init.svc.bootanim"};
            do {
                process = Runtime.getRuntime().exec(commandBootAnim);
                inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                process.waitFor(1, TimeUnit.SECONDS);
                terminalOutput = inputStream.readLine();
                remainingNanos = deadline - System.nanoTime();
            } while ((terminalOutput == null || !terminalOutput.equals("stopped")) && remainingNanos >= 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (remainingNanos < 0)
            throw new TimeoutException("Emulator start timeout");
        System.out.println("Emulator is ready to be used!");
    }

    /**
     * Kills all running emulators
     */
    public static void closeEmulator() {
        if (isEmulatorStarted) {
            System.out.println("Killing emulator...");
            String[] killEmulatorCommand = new String[] { adbPath, "emu", "kill" };
            try {
                Process process = Runtime.getRuntime().exec(killEmulatorCommand);
                process.waitFor(10, TimeUnit.SECONDS);
                System.out.println("Emulator closed successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
