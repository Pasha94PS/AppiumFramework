package com.griddynamics.ppetrenko.pageObjects.apiDemoApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static java.lang.Boolean.*;

public class DependenciesPage {
    public DependenciesPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/checkbox")
    private WebElement checkbox;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
    private WebElement wiFiSettings;

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement input;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
    private WebElement okButton;

    public DependenciesPage activateWiFiCheckbox() {
        boolean isCheckBoxChecked = parseBoolean(checkbox.getAttribute("checked"));
        if (!isCheckBoxChecked)
            checkbox.click();
        return this;
    }

    public DependenciesPage openWiFiSettings() {
        wiFiSettings.click();
        return this;
    }

    public DependenciesPage setWiFiNameTo(String wiFiValue) {
        input.click();
        input.sendKeys(wiFiValue);
        return this;
    }

    public DependenciesPage tapOkButton() {
        okButton.click();
        return this;
    }

    public DependenciesPage checkWiFiNameIsEqualTo (String expectedWiFiName) {
        String actualWiFiName = input.getText();
        Assert.assertEquals(actualWiFiName, expectedWiFiName);
        return this;
    }
}
