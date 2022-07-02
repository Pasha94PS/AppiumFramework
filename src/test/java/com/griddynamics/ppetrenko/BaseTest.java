package com.griddynamics.ppetrenko;

import com.griddynamics.ppetrenko.annotations.FreshInstall;
import com.griddynamics.ppetrenko.helpers.DeviceHelper;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.helpers.AppStateHelper.reinstallApp;
import static com.griddynamics.ppetrenko.helpers.AppStateHelper.relaunchApp;
import static com.griddynamics.ppetrenko.helpers.AppiumServerHelper.*;
import static com.griddynamics.ppetrenko.helpers.BrowserStackHelper.*;
import static com.griddynamics.ppetrenko.helpers.DeviceHelper.*;
import static com.griddynamics.ppetrenko.helpers.EmulatorHelper.*;
import static org.testng.xml.XmlSuite.ParallelMode.*;

public class BaseTest {

    private boolean isItClassFirstTest = true;

    @BeforeSuite
    @Parameters({"isExecutionCrossDevice"})
    public void beforeSuite(@Optional("false") String isExecutionCrossDevice, ITestContext context) throws IOException, InterruptedException {
        PARALLEL_MODE = context.getCurrentXmlTest().getParallel();
        IS_EXECUTION_CROSS_DEVICE = Boolean.parseBoolean(isExecutionCrossDevice);
        BS_BUILD_NAME = generateBuildName(context.getSuite().getName());
        if (!IS_BROWSERSTACK_ENABLED) {
            Process process = Runtime.getRuntime().exec("killall node");
            process.waitFor(3, TimeUnit.SECONDS);
        }
        if (!IS_EXECUTION_CROSS_DEVICE) {
            DeviceHelper.setDeviceToRun();
        }
    }

    @BeforeClass
    @Parameters({"device"})
    public void beforeBaseClass(@Optional String deviceIdFromTestNgXml) {
        if(IS_EXECUTION_CROSS_DEVICE)
            setDeviceToRun(deviceIdFromTestNgXml);
        if (!IS_BROWSERSTACK_ENABLED)
            startAppiumServer();
        if (!IS_BROWSERSTACK_ENABLED && getDeviceToRun().getDeviceUdid().contains("emulator"))
            launchEmulator(getDeviceToRun().getEmulatorName());
    }

    @BeforeMethod
    public void beforeBaseMethod(Method method, Object[] testDataArray, ITestResult testResult) throws InterruptedException {
        StringBuffer testName = new StringBuffer(method.getName());
        if (testDataArray.length > 0){
            StringJoiner testDataJoiner = new StringJoiner(", ", " (", ")");
            Arrays.stream(testDataArray).forEach(testData -> testDataJoiner.add(testData.toString()));
            testName.append(testDataJoiner);
        }
        if (IS_EXECUTION_CROSS_DEVICE)
            testName.append(" - ").append(getDeviceToRun().getDeviceName());
        testResult.setAttribute("testName", testName);

        if (!isItClassFirstTest && !PARALLEL_MODE.equals(METHODS) && !method.isAnnotationPresent(FreshInstall.class))
            relaunchApp();
        if (!isItClassFirstTest && !PARALLEL_MODE.equals(METHODS) && method.isAnnotationPresent(FreshInstall.class))
            reinstallApp();

        isItClassFirstTest = false;
    }

    @AfterMethod
    public void afterBaseMethod() {
        if (PARALLEL_MODE.equals(METHODS))
            driver.get().quit();
    }

    @AfterClass
    public void afterBaseClass(ITestContext context) {
        if (!PARALLEL_MODE.equals(METHODS) && IS_BROWSERSTACK_ENABLED)
            setBsClassSessionResult(context, this.getClass().getName());
        if (!PARALLEL_MODE.equals(METHODS))
            driver.get().quit();
        if (!IS_BROWSERSTACK_ENABLED)
            stopAppiumServer();
        if (!IS_BROWSERSTACK_ENABLED && getDeviceToRun().getDeviceUdid().contains("emulator"))
            closeEmulator();
    }
}