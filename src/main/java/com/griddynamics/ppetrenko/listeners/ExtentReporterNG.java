package com.griddynamics.ppetrenko.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

import static com.griddynamics.ppetrenko.helpers.FileHelper.*;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//      Removing is needed in order to exclude possible confusion when report has not been created for some reason, but
//      you can think of the previous report as being created after this test run.
//      It also prevents the previous run report from being loaded into jenkins in this case.
        removePreviousReport();

        ExtentSparkReporter reporter = new ExtentSparkReporter("output");
        reporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(reporter);

        List<ITestResult> allTestsList = new LinkedList<>();

        for (ISuite suite: suites) {
            Map<String, ISuiteResult> suiteResultsMap = suite.getResults();

            for (ISuiteResult suiteResult : suiteResultsMap.values()) {
                ITestContext context = suiteResult.getTestContext();

                allTestsList.addAll(context.getFailedTests().getAllResults());
                allTestsList.addAll(context.getPassedTests().getAllResults());
                allTestsList.addAll(context.getSkippedTests().getAllResults());
            }
        }

        allTestsList.stream()
                .sorted()
                .forEach(this::publishTest);

        extent.flush();
    }

    private void publishTest(ITestResult testResult) {
        ExtentTest test;
        try {
            test = extent.createTest(testResult.getAttribute("testName").toString());
        } catch (NullPointerException e) {
            System.err.printf("There is no testName attribute for this test, so methodName is used - %s%n",
                    testResult.getMethod().getMethodName());
            test = extent.createTest(testResult.getMethod().getMethodName());
        }


        test.getModel().setStartTime(new Date(testResult.getStartMillis()));
        test.getModel().setEndTime(new Date(testResult.getEndMillis()));

        switch (testResult.getStatus()) {
            case 1:
                test.log(Status.PASS, "Test passed");
                break;
            case 2:
                test.addScreenCaptureFromPath("screenshots" + File.separator + GlobalVariables.SUITE_START_TIME +
                        File.separator + String.format("%s.png", testResult.getAttribute("testName")));
                test.log(Status.FAIL, "Test failed");
                break;
            case 3:
                test.log(Status.SKIP, "Test skipped. Most likely some exception occurred during the set up");
                break;
            default:
                throw new IllegalArgumentException("There is unknown test status");
        }
    }
}

