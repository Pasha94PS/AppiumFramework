package com.griddynamics.ppetrenko.global_variables;

import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.DependenciesPage;
import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.HomePage;
import com.griddynamics.ppetrenko.pageObjects.apiDemoApp.PreferencesPage;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.CheckoutPage;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.FormPage;
import com.griddynamics.ppetrenko.pageObjects.ecommerceApp.ProductListPage;

public class Pages {
    public static ThreadLocal<FormPage> formPage = new ThreadLocal<>();
    public static ThreadLocal<ProductListPage> productListPage = new ThreadLocal<>();
    public static ThreadLocal<CheckoutPage> checkoutPage = new ThreadLocal<>();


    public static ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    public static ThreadLocal<PreferencesPage> preferencesPage = new ThreadLocal<>();
    public static ThreadLocal<DependenciesPage> dependenciesPage = new ThreadLocal<>();
}
