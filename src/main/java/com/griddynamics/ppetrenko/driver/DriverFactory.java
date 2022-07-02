package com.griddynamics.ppetrenko.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.helpers.DeviceHelper.*;
import static com.griddynamics.ppetrenko.helpers.EnvironmentVariablesHelper.*;
public class DriverFactory {

    public static AndroidDriver<AndroidElement> createDriver(String appToExecute, String bsSessionName) throws IOException {
        String appiumUrl;
        DesiredCapabilities cap = new DesiredCapabilities();
        if (IS_BROWSERSTACK_ENABLED) {
            cap.setCapability("browserstack.user", getStringSystemProperty("bsUser"));
            cap.setCapability("browserstack.key", getStringSystemProperty("bsKey"));
            cap.setCapability("device", getDeviceToRun().getDeviceName());
            cap.setCapability("os_version", getDeviceToRun().getOsVersion());
            cap.setCapability("browserstack.idleTimeout", "30");
            cap.setCapability("build", BS_BUILD_NAME);
            cap.setCapability("name", bsSessionName);
            appiumUrl = "http://hub.browserstack.com/wd/hub";
        } else {
            cap.setCapability(MobileCapabilityType.UDID, getDeviceToRun().getDeviceUdid());
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            cap.setCapability(MobileCapabilityType.FULL_RESET, true);
            appiumUrl = "http://127.0.0.1:4723/wd/hub";
        }


        switch (appToExecute) {
            case "GeneralStoreApp": {
                String appPath = getStringSystemProperty("generalStoreAppPath");
                cap.setCapability("appPackage", "com.androidsample.generalstore");
                cap.setCapability(MobileCapabilityType.APP, appPath);
                // lines below is required for installing the app on Browser Stack
                cap.setCapability("appPath", appPath);
                cap.setCapability("browserstack.midSessionInstallApps", new String[] {appPath});
                break;
            }
            case "ApiDemo": {
                String appPath = getStringSystemProperty("apiDemoAppPath");
                cap.setCapability("appPackage", "io.appium.android.apis");
                cap.setCapability(MobileCapabilityType.APP, appPath);
                // lines below is required for installing the app on Browser Stack
                cap.setCapability("browserstack.midSessionInstallApps", new String[] {appPath});
                cap.setCapability("appPath", appPath);
                break;
            }
            default:
                throw new IllegalArgumentException("Provided appToExecute is not recognised. Provide valid appToExecute value");
        }

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL(appiumUrl), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
