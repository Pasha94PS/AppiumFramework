package com.griddynamics.ppetrenko.global_variables;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.xml.XmlSuite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.griddynamics.ppetrenko.helpers.EnvironmentVariablesHelper.*;

public class GlobalVariables {
    public static final boolean IS_BROWSERSTACK_ENABLED = getBooleanSystemPropOrReturnDefault("useBrowserstack", false);
    public static XmlSuite.ParallelMode PARALLEL_MODE;
    public static ThreadLocal<AndroidDriver<AndroidElement>> driver = new ThreadLocal<>();
    public static String BS_BUILD_NAME;
    public static boolean IS_EXECUTION_CROSS_DEVICE;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String SUITE_START_TIME = LocalDateTime.now().format(DATE_TIME_FORMAT);
}

