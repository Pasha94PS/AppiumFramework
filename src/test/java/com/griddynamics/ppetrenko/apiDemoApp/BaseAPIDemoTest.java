package com.griddynamics.ppetrenko.apiDemoApp;

import com.griddynamics.ppetrenko.BaseTest;
import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.DependenciesPage;
import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.HomePage;
import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.PreferencesPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static com.griddynamics.ppetrenko.driver.DriverFactory.createDriver;
import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.global_variables.Pages.*;
import static com.griddynamics.ppetrenko.helpers.AppStateHelper.relaunchApp;
import static org.testng.xml.XmlSuite.ParallelMode.METHODS;

public class BaseAPIDemoTest extends BaseTest {
    private boolean isItClassFirstTest = true;

    @BeforeClass
    @Parameters({"device"})
    public void beforeClass(@Optional String deviceIdFromTestNXml) throws IOException {
        if(!PARALLEL_MODE.equals(METHODS)) {
            AndroidDriver<AndroidElement> driver = createDriver("ApiDemo",
                    this.getClass().getSimpleName());
            GlobalVariables.driver.set(driver);
            initialiseAllPages(driver);
        }
    }

    @BeforeMethod
    public void beforeMethod(ITestResult testResult) throws IOException {
        if(PARALLEL_MODE.equals(METHODS)) {
            AndroidDriver<AndroidElement> driver = createDriver("ApiDemo",
                    testResult.getAttribute("testName").toString());
            GlobalVariables.driver.set(driver);
            initialiseAllPages(driver);
        }
    }

    private void initialiseAllPages(AndroidDriver<AndroidElement> driver) {
        homePage.set(new HomePage(driver));
        preferencesPage.set(new PreferencesPage(driver));
        dependenciesPage.set(new DependenciesPage(driver));
    }
}
