package com.jumia;

import com.jumia.base.BaseClass;
import com.jumia.pages.CartPage;
import com.jumia.pages.HomePage;
import com.jumia.pages.LoginPage;
import com.jumia.pages.ShirtsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testx extends BaseClass {
    @BeforeTest
    public void setUp() {
        loadConfig();
        launchWeb();
    }

    @Test
    public void TC() {
        HomePage homePageObj = new HomePage();
        LoginPage loginPageObj = new LoginPage();
        ShirtsPage shirtsPageObj = new ShirtsPage();
        CartPage cartPageObj =new CartPage();
        homePageObj.skipWelcomePopUp();
        homePageObj.openLoginPage();
//        loginPageObj.registerUser("jamefij283a000@wiroute.com", "Test@1234@",
//                "aaa", "xxx", "01122232321","12201997");
//        boolean result = loginPageObj.validateAccountIsCreated();
//        Assert.assertTrue(result);
        loginPageObj.login("jamefij283a000@wiroute.com", "Test@1234@");
        homePageObj.openCategoryPage();
        shirtsPageObj.addTProduct1ToCart();
        shirtsPageObj.addTProduct2ToCart();
        driver.navigate().refresh();
        Assert.assertEquals(cartPageObj.calculateProductsTotalPrice(),cartPageObj.subTotalPrice());
    }
}
