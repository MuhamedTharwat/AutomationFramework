package com.amazon.tests;

import base.BaseClass;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.VideoGamePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
        homePageObj.openLoginPage();
        loginPageObj.login("dxsheetos@gmail.com","XjgGY.5bu@5yPJq");
        //homePageObj.navigateToVideoGamesSection();
        //homePageObj.switchLanguageEN();
        homePageObj.navigateToSection("Video Games","All Video Games");
        videoGamePageObj.filterByFreeShipping();
        videoGamePageObj.sortByPriceHighToLow();
        //videoGamePageObj.filterByCost();
        videoGamePageObj.test();

    }
}
