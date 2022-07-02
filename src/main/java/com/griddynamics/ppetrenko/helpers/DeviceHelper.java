package com.griddynamics.ppetrenko.helpers;

import com.griddynamics.ppetrenko.devices.Device;

import java.util.Map;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.helpers.EnvironmentVariablesHelper.*;

public class DeviceHelper {
    private static Device device;
    private static ThreadLocal<Device> deviceForCrossDeviceExecution = new ThreadLocal<>();
    private static Map<String, Device> availableDevices = JSONHelper.loadDevicesFromJson();

    public static Device getDeviceToRun() {
        return IS_EXECUTION_CROSS_DEVICE ? deviceForCrossDeviceExecution.get() : device;
    }
    public static void setDeviceToRun(String deviceIdFromTestNgXml) {
        if (deviceIdFromTestNgXml == null)
                throw new IllegalArgumentException("Device parameter is not provided in testNG XML." +
                        "It is required for cross-device test execution");
        if (!availableDevices.containsKey(deviceIdFromTestNgXml))
                throw new IllegalArgumentException(String.format("There is no device with id '%s'", deviceIdFromTestNgXml));
        deviceForCrossDeviceExecution.set(availableDevices.get(deviceIdFromTestNgXml));
    }

    public static void setDeviceToRun() {
        String deviceIdFromEnvVariable = getStringSystemPropOrReturnDefault("device", "Default");
        if (!availableDevices.containsKey(deviceIdFromEnvVariable))
            throw new IllegalArgumentException(String.format("There is no device with id '%s'", deviceIdFromEnvVariable));
        device = availableDevices.get(deviceIdFromEnvVariable);
    }

}
