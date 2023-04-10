package com.amazon.tests;

import base.BaseClass;
import com.amazon.pages.HomePage;
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
        homePageObj.openLoginPage();

    }
}
