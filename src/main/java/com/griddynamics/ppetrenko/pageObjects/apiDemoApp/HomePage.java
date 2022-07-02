package com.griddynamics.ppetrenko.pageObjects.apiDemoApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.griddynamics.ppetrenko.global_variables.Pages.*;

public class HomePage {
    public HomePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
    private WebElement preference;

    public PreferencesPage goToPreferences() {
        preference.click();
        return preferencesPage.get();
    }
}
