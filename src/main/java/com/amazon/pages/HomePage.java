package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class HomePage extends BaseClass {
    Action action = new Action();
    By languagesList = By.id("icp-nav-flyout");
    By accountList = By.id("nav-link-accountList");
    By languageEn = By.xpath("//*[@id='nav-flyout-icp']//span[contains(text(),'En')]");
    By languageAR = By.xpath("//*[@id='nav-flyout-icp']//span[contains(text(),'AR')]");

    public void switchLanguageEN()
    {
        action.jsHoverOnElement(languagesList);
        action.click(languageEn);
    }
    public void openLoginPage(){
        action.jsHoverOnElement(accountList);
        action.click(By.id("nav-flyout-ya-signin"));
    }
}
