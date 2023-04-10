package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class HomePage extends BaseClass {
    Action action = new Action();
    By languagesList = By.id("icp-nav-flyout");
    By accountList = By.id("nav-link-accountList");
    By signInBtn = By.id("nav-flyout-ya-signin");
    By languageEn = By.xpath("//*[@id='nav-flyout-icp']//span[contains(text(),'En')]");
    By languageAR = By.xpath("//*[@id='nav-flyout-icp']//span[contains(text(),'AR')]");
    By allNavMenu = By.id("nav-hamburger-menu");
    By seeAllList = By.xpath("//div[@id='hmenu-content']//div[normalize-space()='see all']");
    By videoGamesList = By.xpath("//div[@id='hmenu-content']//li[normalize-space()='Video Games']");
    By allVideoGamesOption = By.xpath("//div[@id='hmenu-content']//li[normalize-space()='All Video Games']");

    public void switchLanguageEN()
    {
        action.jsHoverOnElement(languagesList);
        action.click(languageEn);
    }public void switchLanguageAR()
    {
        action.jsHoverOnElement(languagesList);
        action.click(languageAR);
    }
    public void openLoginPage(){
        action.jsHoverOnElement(accountList);
        action.click(signInBtn);
    }
    public void navigateToVideoGamesSection(){
        action.click(allNavMenu);
        action.click(seeAllList);
        action.click(videoGamesList);
        action.click(allVideoGamesOption);
    }
    //this a more dynamic function to navigate to any section and select one of its options
    public void navigateToSection(String sectionName,String selectedOptionName){
        action.click(allNavMenu);
        action.click(seeAllList);
        action.click(By.xpath(String.format
                ("//div[@id='hmenu-content']//li[normalize-space()='%s']",sectionName)));
        action.click(By.xpath(String.format
                ("//div[@id='hmenu-content']//li[normalize-space()='%s']",selectedOptionName)));
    }
}
