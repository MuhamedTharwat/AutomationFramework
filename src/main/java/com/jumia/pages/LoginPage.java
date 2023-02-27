package com.jumia.pages;

import com.jumia.actions.Action;
import com.jumia.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BaseClass {
    Action action= new Action();
    Actions ac = new Actions(driver);
    By emailTxtBox = By.name("email");
    By continueBtn1 = By.xpath("//button[@type='submit'][contains(normalize-space(),'Continue')]");
    By continueBtn2 = By.xpath("//*[@id='card_password']//button");
    By continueBtn3 = By.xpath("//*[@id='card_profile_details']//button");
    By continueBtn4 = By.id("confirmBtn");
    By passwordTxtBox = By.name("password");
    By confirmPasswordTxtBox = By.xpath("//*[contains(@class, 'confirm-password-input')]");
    By passwordStrengthBox = By.className("password-meter");
    By firstNameTxtBox = By.id("input_first_name");
    By lastNameTxtBox = By.id("input_last_name");
    By phoneNumberTxtBox = By.name("phone[number]");
    By phoneCode = By.name("phone[prefix]");
    By genderDropDown = By.xpath("//div[@id='gender']");
    By gender = By.xpath("//div[@id='gender']//*[@data-value='M']");
    By birthDayCalender = By.id("input_birth_date");
    By termsChkBox = By.id("acceptTC");
    By loginBtn = By.id("loginButton");
    public void registerUser(String email,String password,
                             String firstName,String lastName,String phoneNumber,String birthDay){
        action.type(emailTxtBox,email);
        action.click(continueBtn1);
        action.type(passwordTxtBox,password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        action.type(confirmPasswordTxtBox,password);
        action.click(continueBtn2);
        action.type(firstNameTxtBox,firstName);
        action.type(lastNameTxtBox,lastName);
        action.type(phoneNumberTxtBox,phoneNumber);
        action.click(continueBtn3);
        action.click(genderDropDown);
        action.click(gender);
        action.type(birthDayCalender,birthDay);
        action.selectCheckBox(termsChkBox);
        action.click(continueBtn4);
    }

    public boolean validateAccountIsCreated(){
        boolean flag = driver.getCurrentUrl().contains("sign-up/success");
        return flag;
    }

    public void login(String email,String password){
        action.type(emailTxtBox,email);
        action.click(continueBtn1);
        action.type(passwordTxtBox,password);
        action.click(loginBtn);
    }
}
