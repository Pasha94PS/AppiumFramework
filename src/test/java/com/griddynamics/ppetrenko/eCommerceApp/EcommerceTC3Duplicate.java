package com.griddynamics.ppetrenko.eCommerceApp;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.griddynamics.ppetrenko.global_variables.Pages.formPage;

public class EcommerceTC3Duplicate extends BaseECommerceAppTest {


    @Test
    public void totalTestDuplicate() {
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
    public void totalTestDuplicate2() {
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