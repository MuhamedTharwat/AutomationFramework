package com.amazon.tests;

import base.BaseClass;
import com.amazon.pages.CartPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.VideoGamePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class DummyClass extends BaseClass {
    @BeforeTest
    public void setUp(){
        loadConfig();
        launchWeb();
    }
    @Test
    public void tc (){
        HomePage homePageObj = new HomePage();
        LoginPage loginPageObj = new LoginPage();
        VideoGamePage videoGamePageObj = new VideoGamePage();
        CartPage cartPageObj = new CartPage();
        homePageObj.openLoginPage();
        loginPageObj.login("dxsheetos@gmail.com","XjgGY.5bu@5yPJq");
        //homePageObj.navigateToVideoGamesSection();
        //homePageObj.switchLanguageEN();
        homePageObj.navigateToSection("Video Games","All Video Games");
        videoGamePageObj.filterByFreeShipping();
        videoGamePageObj.sortByPriceHighToLow();
        //videoGamePageObj.filterByCost();
        videoGamePageObj.test();
        List<String> names = videoGamePageObj.getProductsNames();
        homePageObj.openCartPage();
        List<String> cartNames = cartPageObj.getCartProductsNames();
        boolean x =cartNames.stream().allMatch(s -> names.contains(s));

    }
}
