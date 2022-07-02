package com.griddynamics.ppetrenko.eCommerceApp;

import com.griddynamics.ppetrenko.BaseTest;
import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.CheckoutPage;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.FormPage;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.ProductListPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static com.griddynamics.ppetrenko.driver.DriverFactory.*;
import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.global_variables.Pages.*;
import static org.testng.xml.XmlSuite.ParallelMode.*;

public class BaseECommerceAppTest extends BaseTest {

    @BeforeClass
    @Parameters({"device"})
    public void beforeClass(@Optional String deviceIdFromTestNXml) throws IOException {
        if(!PARALLEL_MODE.equals(METHODS)) {
            AndroidDriver<AndroidElement> driver = createDriver("GeneralStoreApp",
                    this.getClass().getSimpleName());
            GlobalVariables.driver.set(driver);
            initialiseAllPages(driver);
        }
    }

    @BeforeMethod
    public void beforeMethod(ITestResult testResult) throws IOException {
        if(PARALLEL_MODE.equals(METHODS)) {
            AndroidDriver<AndroidElement> driver = createDriver("GeneralStoreApp",
                                                                testResult.getAttribute("testName").toString());
            GlobalVariables.driver.set(driver);
            initialiseAllPages(driver);
        }
    }

    private void initialiseAllPages(AndroidDriver<AndroidElement> driver) {
        formPage.set(new FormPage(driver));
        productListPage.set(new ProductListPage(driver));
        checkoutPage.set(new CheckoutPage(driver));
    }
}
