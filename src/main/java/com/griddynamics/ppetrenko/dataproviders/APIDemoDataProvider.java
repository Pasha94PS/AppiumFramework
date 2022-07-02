package com.griddynamics.ppetrenko.dataproviders;

import org.testng.annotations.DataProvider;

public class APIDemoDataProvider {

    @DataProvider (name = "Wi-Fi test data")
    public Object[][] getApiDemoWiFiTestData() {
        return new Object[][] {
                {"£$%^&^%$"},
                {"hello"}
        };
    }
}
