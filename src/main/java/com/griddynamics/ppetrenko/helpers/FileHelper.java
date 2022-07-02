package com.griddynamics.ppetrenko.helpers;

import java.io.File;

import static com.griddynamics.ppetrenko.global_variables.GlobalVariables.*;

public class FileHelper {
    public static void removePreviousReport() {
        File reportFile = new File("output/Index.html");
        if (!reportFile.exists()) {
            System.err.println("Report file is not exist\n");
            return;
        }
        if (reportFile.delete()) {
            System.err.println("Previous reports is deleted\n");
            return;
        }
        System.err.println("Failed to delete previous report\n");
    }

    public static void createDirectoryForScreenshots() {
        File screenshotsDirectory = new File("output/screenshots/" + SUITE_START_TIME);
        if (screenshotsDirectory.exists()) {
            System.err.printf("%s folder for screenshots is already exist%n", screenshotsDirectory.getAbsolutePath());
            return;
        }
        if (screenshotsDirectory.mkdir()) {
            System.err.printf("%s folder for screenshots is created%n", screenshotsDirectory.getAbsolutePath());
            return;
        }
        System.err.printf("Failed to create %s directory%n", screenshotsDirectory.getAbsolutePath());
    }
}
