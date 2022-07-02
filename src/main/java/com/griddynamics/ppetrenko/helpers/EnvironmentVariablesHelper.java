package com.griddynamics.ppetrenko.helpers;

import java.util.function.Function;

public class EnvironmentVariablesHelper {
    private static <T> T getSystemPropOrReturnDefault(String property, T defaultValue, Function<String, T> function) {
        String propertyValue = System.getProperty(property);
        return propertyValue == null ? defaultValue : function.apply(propertyValue);
    }

    public static boolean getBooleanSystemPropOrReturnDefault(String property, boolean defaultValue) {
        return getSystemPropOrReturnDefault(property, defaultValue, Boolean::parseBoolean);
    }

    public static String getStringSystemPropOrReturnDefault(String property, String defaultValue) {
        return getSystemPropOrReturnDefault(property, defaultValue, String::toString);
    }

    public static String getStringSystemProperty(String property) {
        String propertyValue = System.getProperty(property);
        if (propertyValue == null)
            throw new IllegalStateException(String.format("%s environment variable is not set", property));
        return propertyValue;


    }
}
