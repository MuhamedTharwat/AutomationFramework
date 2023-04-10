package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class LoginPage extends BaseClass {
    Action action = new Action();
    By emailTxtBox = By.id("ap_email");
    By passwordTxtBox = By.id("ap_password");
    By continueBtn = By.id("continue");
    By signInBtn = By.id("signInSubmit");

    public void login(String email,String password){
        action.type(emailTxtBox,email);
        action.click(continueBtn);
        action.type(passwordTxtBox,password);
        action.click(signInBtn);
    }

}
