package com.griddynamics.ppetrenko.helpers;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AppiumServerHelper {
    private static AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    private static boolean isServerProgrammaticallyStarted = false;

    public static void startAppiumServer() {
        if (!checkIfServerIsRunning(4723)) {
            service.start();
            isServerProgrammaticallyStarted = true;
        }
    }

    public static void stopAppiumServer() {
        if (isServerProgrammaticallyStarted)
            service.stop();
    }

    private static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = true;
        try {
            URL url = new URL(String.format("http://127.0.0.1:%d/wd/hub", port));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();
        } catch (IOException e) {
            isServerRunning = false;
        }
        System.out.println(isServerRunning);
        return isServerRunning;
    }
}
