package com.griddynamics.ppetrenko.apiDemoApp;

import com.griddynamics.ppetrenko.dataproviders.APIDemoDataProvider;

import org.testng.annotations.Test;

import java.io.IOException;

import static com.griddynamics.ppetrenko.global_variables.Pages.homePage;

public class ApiDemoTest extends BaseAPIDemoTest {

    @Test (dataProvider = "Wi-Fi test data", dataProviderClass = APIDemoDataProvider.class)
    public void ApiDemoTest(String inputData) throws IOException {
        homePage.get()
                .goToPreferences()
                .goToPreferenceDependencies()
                .activateWiFiCheckbox()
                .openWiFiSettings()
                .setWiFiNameTo(inputData)
                .tapOkButton()
                .openWiFiSettings()
                .checkWiFiNameIsEqualTo(inputData);
    }
}
