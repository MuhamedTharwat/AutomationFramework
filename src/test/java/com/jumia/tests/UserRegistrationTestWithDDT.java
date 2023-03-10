package com.jumia.tests;

import com.jumia.tests.base.BaseClass;
import com.jumia.tests.pages.CartPage;
import com.jumia.tests.pages.HomePage;
import com.jumia.tests.pages.LoginPage;
import com.jumia.tests.pages.ShirtsPage;
import com.jumia.tests.utility.DataProviders;
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

    @Test(priority = 1,dataProvider = "Credentials",dataProviderClass = DataProviders.class)
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
            dataProvider = "Login",dataProviderClass = DataProviders.class)
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
