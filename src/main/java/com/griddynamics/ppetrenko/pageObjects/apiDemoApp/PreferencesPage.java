package com.griddynamics.ppetrenko.pageObjects.apiDemoApp;

import com.griddynamics.ppetrenko.global_variables.Pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PreferencesPage {
    public PreferencesPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
    private WebElement preferenceDependencies;

    public DependenciesPage goToPreferenceDependencies() {
        preferenceDependencies.click();
        return Pages.dependenciesPage.get();
    }
}
