package com.griddynamics.ppetrenko.listeners;

import com.griddynamics.ppetrenko.helpers.BrowserStackHelper;
import com.griddynamics.ppetrenko.helpers.ScreenShotHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.aventstack.extentreports.Status.*;
import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static org.testng.xml.XmlSuite.ParallelMode.*;

public class Listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if (PARALLEL_MODE.equals(METHODS))
            BrowserStackHelper.setBsSessionResult(PASS);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getAttribute("testName").toString();
        ScreenShotHelper.takeScreenshot(testName, driver.get());
        if (PARALLEL_MODE.equals(METHODS))
            BrowserStackHelper.setBsSessionResult(FAIL);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
