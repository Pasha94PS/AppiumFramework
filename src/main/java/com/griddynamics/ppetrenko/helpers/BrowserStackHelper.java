package com.griddynamics.ppetrenko.helpers;

import com.aventstack.extentreports.Status;
import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static com.aventstack.extentreports.Status.FAIL;
import static com.aventstack.extentreports.Status.PASS;
import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;
import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.driver;

public class BrowserStackHelper {
    public static String generateBuildName(String suiteName) {
        switch (suiteName) {
            case "Regression":
                return "Regression tests " + SUITE_START_TIME;
            case "Smoke":
                return "Smoke tests " + SUITE_START_TIME;
            case "Browser Stack Cross-device Regression":
                return "Browser Stack Cross-device Regression " + SUITE_START_TIME;
            default:
                return "NA tests " + SUITE_START_TIME;
        }
    }

    public static void setBsClassSessionResult(ITestContext context, String callingTestClassName) {
        List<ITestResult> thisClassFailedTests = context.getFailedTests().getAllResults()
                .stream()
                .filter(test -> test.getTestClass().getName().equals(callingTestClassName))
                .collect(Collectors.toCollection(LinkedList::new));

        if (thisClassFailedTests.isEmpty()) {
            setBsSessionResult(PASS);
        } else {
            StringJoiner failedTestsNames = new StringJoiner(", ");
            thisClassFailedTests.forEach(test -> failedTestsNames.add(test.getAttribute("testName").toString()));
            setBsSessionResult(FAIL, "Failed tests: " + failedTestsNames);
        }
    }

    public static void setBsSessionResult(Status status) {
        setBsSessionResult(status, "");
    }

    public static void setBsSessionResult(Status status, String reason) {
        String sessionStatus;
        switch (status) {
            case PASS:
                sessionStatus = "passed";
                break;
            case FAIL:
                sessionStatus = "failed";
                break;
            default:
                throw new IllegalArgumentException("Incorrect status parameter");
        }

        driver.get().executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\"," +
                " \"arguments\": {\"status\": \"%s\", \"reason\": \"%s\"}}", sessionStatus, reason));
    }
}
