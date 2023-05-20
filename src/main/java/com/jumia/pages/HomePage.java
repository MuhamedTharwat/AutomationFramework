package com.jumia.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class HomePage extends BaseClass {
    Action action = new Action();
    By welcomePopUp = By.id("pop");
    By closePopUpBtn = By.xpath("//div[@id='pop']//button[@class='cls']");
    By accountDropDown = By.xpath("//*[@id='dpdw-login']//parent::div[@class='dpdw _pcent']");
    By signInPageBtn = By.xpath("//a[normalize-space()='Sign In'][contains(@href, 'account/login')]");
    By logOutBtn = By.xpath("//div[@id='dpdw-login-box']//button[normalize-space()='Logout']");
    By fashionCategory = By.xpath("//a[@href='/category-fashion-by-jumia/']");
    By shirtsCategory = By.xpath("//a[normalize-space()='Shirts']");
    By cartPage = By.xpath("//a[normalize-space()='Cart']");

    public void skipWelcomePopUp() {
        if (action.findElement(welcomePopUp)) {
            action.click(closePopUpBtn);
        }
        return;
    }

    public void openLoginPage() {
        action.click(accountDropDown);
        action.click(signInPageBtn);
    }

    public void navigateToShirtsCategory() {
        action.hoverOnElement(fashionCategory);
        action.click(shirtsCategory);
    }

    public void openCartPage() {
        action.click(cartPage);
    }

    public void logOut() {
        action.click(accountDropDown);
        action.click(logOutBtn);
    }
}
