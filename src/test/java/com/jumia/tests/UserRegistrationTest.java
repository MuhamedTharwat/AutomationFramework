package com.jumia.tests;

import base.BaseClass;
import com.jumia.pages.CartPage;
import com.jumia.pages.HomePage;
import com.jumia.pages.LoginPage;
import com.jumia.pages.ShirtsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseClass {
    HomePage homePageObj;
    LoginPage loginPageObj;
    ShirtsPage shirtsPageObj;
    CartPage cartPageObj;

    @BeforeTest
    public void setUp() {
        loadConfig();
        launchWeb();
    }

    @Test(priority = 1)
    public void Task1VerifyUserRegistration() {
        homePageObj = new HomePage();
        loginPageObj = new LoginPage();
        shirtsPageObj = new ShirtsPage();
        cartPageObj = new CartPage();

        homePageObj.skipWelcomePopUp();
        homePageObj.openLoginPage();
        loginPageObj.registerUser("jamefij283a1005@wiroute.com", "Test@1234@",
                "aaa", "xxx", "01224232329", "12201997");
        Assert.assertTrue(loginPageObj.validateAccountIsCreated());
        homePageObj.logOut();
    }

    @Test(dependsOnMethods = ("Task1VerifyUserRegistration"))
    public void Task2LoginWithCreatedUserAndAddProducts() {
        homePageObj = new HomePage();
        loginPageObj = new LoginPage();
        shirtsPageObj = new ShirtsPage();
        cartPageObj = new CartPage();

        //homePageObj.skipWelcomePopUp();
        homePageObj.openLoginPage();
        loginPageObj.login("jamefij283a0002@wiroute.com", "Test@1234@");
        homePageObj.navigateToShirtsCategory();
        shirtsPageObj.addTProduct1ToCart();
        shirtsPageObj.addTProduct2ToCart();
        refreshCurrentPage();
        Assert.assertEquals(cartPageObj.calculateProductsTotalPrice(), cartPageObj.subTotalPrice());

    }

    @AfterTest
    public void tearDown() {
        //closeCurrentPage();
    }

}
