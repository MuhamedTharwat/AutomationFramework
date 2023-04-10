package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class VideoGamePage extends BaseClass {
    Action action = new Action();
    By freeShippingFilter = By.xpath("//div[@id='s-refinements']//*[normalize-space()='Free Shipping']");
    By sortFeatureBtn = By.id("s-result-sort-select");

    public void filter(){
        action.click(freeShippingFilter);
        action.jsClickOnElement(sortFeatureBtn);
    }
}
