package com.griddynamics.ppetrenko.eCommerceApp;

import com.griddynamics.ppetrenko.annotations.FreshInstall;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.griddynamics.ppetrenko.global_variables.Pages.formPage;

public class EcommerceTC3 extends BaseECommerceAppTest {


    @Test
    @FreshInstall
    public void totalTest() {
        formPage.get()
                .enterName("hello")
                .checkFemaleCheckbox()
                .selectCountry("Albania")
                .clickLetsShopButton()
                .addItemsToTheCart(2)
                .goToCart()
                .waitUntilCartIsOpened()
                .verifyThatTotalHasCorrectValue();
    }

    @Test
    @FreshInstall
    public void totalTest2() {
        formPage.get()
                .enterName("hello")
                .checkFemaleCheckbox()
                .selectCountry("Albania")
                .clickLetsShopButton()
                .addItemsToTheCart(2)
                .goToCart()
                .waitUntilCartIsOpened()
                .verifyThatTotalHasCorrectValue();
    }
}