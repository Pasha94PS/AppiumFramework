package com.griddynamics.ppetrenko.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InteractionHelper {

    // This method is needed because sometimes Android Scroll failed before reaching end of the list.
    public static void scrollToAndTapElement(AndroidDriver<AndroidElement> driver, String elementText) {
        try {
            driver.findElementByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"%s\"))", elementText)).click();
        }catch (NoSuchElementException e){
            System.out.println("Android scroll failed");
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            while (true) {
                try{
                    driver.findElementByXPath(String.format("//android.widget.TextView[@text='%s']", elementText)).click();
                    break;
                }catch (NoSuchElementException ex) {
                    String pageSourceBeforeScroll = driver.getPageSource();
                    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
                    String pageSourceAfterScroll = driver.getPageSource();
                    if (pageSourceBeforeScroll.equals(pageSourceAfterScroll))
                        throw new NoSuchElementException("End of the list is reached and element is not found");
                }
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}