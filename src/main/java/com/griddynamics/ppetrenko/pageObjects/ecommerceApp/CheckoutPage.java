package com.griddynamics.ppetrenko.pageObjects.ecommerceApp;

import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import com.griddynamics.ppetrenko.pageObjects.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.griddynamics.ppetrenko.helpers.ConvertationHelper.convertMoneyAmountToDouble;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CheckoutPage extends BasePage {
    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy (id = "com.androidsample.generalstore:id/productPrice")
    public List<WebElement> priceOfItems;

    @AndroidFindBy (id = "com.androidsample.generalstore:id/totalAmountLbl")
    public WebElement total;

    public CheckoutPage waitUntilCartIsOpened() {
        WebDriverWait wait = new WebDriverWait(GlobalVariables.driver.get(), 10);
        wait.until(visibilityOf(total));
        return this;
    }

    public CheckoutPage verifyThatTotalHasCorrectValue() {
        double expectedTotal = 0;
        for (WebElement price:priceOfItems) {
            expectedTotal += convertMoneyAmountToDouble(price.getText());
        }
        double actualTotal = convertMoneyAmountToDouble(total.getText());
        Assert.assertEquals(actualTotal, expectedTotal);
        return this;
    }

}
