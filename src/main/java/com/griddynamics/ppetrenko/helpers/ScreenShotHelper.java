package com.griddynamics.ppetrenko.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.helpers.FileHelper.*;

public class ScreenShotHelper {
    public static void takeScreenshot(String testName, AndroidDriver<AndroidElement> driver) {
        createDirectoryForScreenshots();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(String.format("output/screenshots/%s/%s.png", SUITE_START_TIME, testName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
