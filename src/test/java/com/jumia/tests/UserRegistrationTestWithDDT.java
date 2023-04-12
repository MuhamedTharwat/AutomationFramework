package com.jumia.tests;

import base.BaseClass;
import com.jumia.pages.CartPage;
import com.jumia.pages.HomePage;
import com.jumia.pages.LoginPage;
import com.jumia.pages.ShirtsPage;
import utility.DataProviders;
import org.testng.Assert;
import org.testng.annotations.*;

public class UserRegistrationTestWithDDT extends BaseClass {
    HomePage homePageObj=null;
    LoginPage loginPageObj=null;
    ShirtsPage shirtsPageObj=null;
    CartPage cartPageObj=null;

    @BeforeTest
    public void setUp() {
        loadConfig();
        launchWeb();
    }

    @Test(priority = 1,dataProvider = "JumiaCredentials",dataProviderClass = DataProviders.class)
    public void Task1VerifyUserRegistration(String email, String password,
                                            String fname, String lname, String phone, String bdate) {
        homePageObj = new HomePage();
        loginPageObj = new LoginPage();

        homePageObj.skipWelcomePopUp();
        homePageObj.openLoginPage();
        loginPageObj.registerUser(email, password,fname,lname,phone,bdate);
        Assert.assertTrue(loginPageObj.validateAccountIsCreated());
        homePageObj.logOut();
    }

    @Test(dependsOnMethods = ("Task1VerifyUserRegistration"),
            dataProvider = "JumiaLogin",dataProviderClass = DataProviders.class)
    public void Task2LoginWithCreatedUserAndAddProducts(String email,String password) {
        homePageObj = new HomePage();
        loginPageObj = new LoginPage();
        shirtsPageObj = new ShirtsPage();
        cartPageObj = new CartPage();

        //homePageObj.skipWelcomePopUp(); //uncomment and remove dependsOnMethods if u want to run this TC without dependency
        homePageObj.openLoginPage();
        loginPageObj.login(email, password);
        homePageObj.navigateToShirtsCategory();
        shirtsPageObj.addTProduct1ToCart();
        shirtsPageObj.addTProduct2ToCart();
        refreshCurrentPage();
        Assert.assertEquals(cartPageObj.calculateProductsTotalPrice(), cartPageObj.subTotalPrice());

    }
    @AfterTest
    public void tearDown(){
        closeCurrentPage();
    }

}
