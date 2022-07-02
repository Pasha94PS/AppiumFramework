package com.griddynamics.ppetrenko.pageObjects.ecommerceApp;

import com.griddynamics.ppetrenko.global_variables.GlobalVariables;
import com.griddynamics.ppetrenko.global_variables.Pages;
import com.griddynamics.ppetrenko.pageObjects.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.griddynamics.ppetrenko.global_variables.Pages.*;

@Getter
public class ProductListPage extends BasePage {
    public ProductListPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy (xpath = "//*[@text='ADD TO CART']")
    public List<WebElement> addToCartButtons;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement cartButton;

    public ProductListPage addItemsToTheCart(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            addToCartButtons.get(0).click();
        }
        return this;
    }

    public CheckoutPage goToCart() {
        cartButton.click();
        return checkoutPage.get();
    }
}
