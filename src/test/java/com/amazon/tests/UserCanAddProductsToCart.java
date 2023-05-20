package com.amazon.tests;

import base.BaseClass;
import com.amazon.pages.CartPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.VideoGamePage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.DataProviders;

import java.util.List;

public class UserCanAddProductsToCart extends BaseClass {
    @BeforeTest
    public void setUp() {
        loadConfig();
        launchWeb();
    }

    @Test(dataProvider = "AmazonLogin", dataProviderClass = DataProviders.class)
    public void tc(String email, String password) {
        HomePage homePageObj = new HomePage();
        LoginPage loginPageObj = new LoginPage();
        VideoGamePage videoGamePageObj = new VideoGamePage();
        CartPage cartPageObj = new CartPage();
        refreshCurrentPage();
        homePageObj.openLoginPage();

        loginPageObj.login(email, password);
        homePageObj.navigateToSection("Video Games", "All Video Games");
        videoGamePageObj.filterByFreeShipping();
        videoGamePageObj.filterByNew();
        videoGamePageObj.sortByPriceHighToLow();

        List<String> names = videoGamePageObj.getAllProductsNamesBelowPrice(10000);
        videoGamePageObj.addALLProductsToCartByBelowPrice(10000);
        homePageObj.openCartPage();
        List<String> cartNames = cartPageObj.getCartProductsNames();
        //compare products names with actual name added in the cart page
        boolean result = cartNames.stream().allMatch(s -> names.contains(s));
        Assert.assertTrue(result);

    }
}
