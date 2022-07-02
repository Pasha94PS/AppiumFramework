package com.griddynamics.ppetrenko.pageObjects.ecommerceApp;

import com.griddynamics.ppetrenko.helpers.InteractionHelper;
import com.griddynamics.ppetrenko.pageObjects.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static com.griddynamics.ppetrenko.global_variables.Pages.*;

public class FormPage extends BasePage {
    public FormPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    public WebElement femaleRadioButton;

    @AndroidFindBy(id = "android:id/text1")
    public WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public WebElement letsShop;

    public FormPage enterName(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
        return this;
    }

    public FormPage selectCountry(String country) {
        countryDropdown.click();
        InteractionHelper.scrollToAndTapElement(driver, country);
        return this;
    }

    public FormPage checkFemaleCheckbox() {
        femaleRadioButton.click();
        return this;
    }
    public ProductListPage clickLetsShopButton() {
        letsShop.click();
        return productListPage.get();
    }
}

