package com.griddynamics.ppetrenko.helpers;

import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.IS_BROWSERSTACK_ENABLED;

public class AppStateHelper {
    public static void relaunchApp() throws InterruptedException {
        AndroidDriver<AndroidElement> driver = GlobalVariables.driver.get();
        try {
            driver.terminateApp(driver.getCapabilities().getCapability("appPackage").toString());
        } catch (WebDriverException e) {
            System.out.println("Termination exception ---> " + e);
        }
        Thread.sleep(1000);
        driver.activateApp(driver.getCapabilities().getCapability("appPackage").toString());
        if (IS_BROWSERSTACK_ENABLED)
            driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public static void reinstallApp() {
        AndroidDriver<AndroidElement> driver = GlobalVariables.driver.get();

        driver.removeApp(driver.getCapabilities().getCapability("appPackage").toString());
        driver.installApp(driver.getCapabilities().getCapability("appPath").toString());
        driver.activateApp(driver.getCapabilities().getCapability("appPackage").toString());
    }
}
