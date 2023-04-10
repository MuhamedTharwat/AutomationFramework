package com.jumia.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class ShirtsPage extends BaseClass {
    Action action = new Action();
    By product1 = By.xpath("//section[@class='card -fh']//img[1]");
    By product2 = By.xpath("//section[@class='card -fh']//img[2]");
    By addToCartProduct1Btn = By.xpath("//section[@class='card -fh']//article[1]//button");
    By addToCartProduct2Btn = By.xpath("//section[@class='card -fh']//article[2]//button");
    By addQtyBtn = By.xpath("(//button[@name='action'])[2]");
    By closeAddToCartBtn = By.xpath("//button[@aria-label='close popup']");
    By viewCartAndCheckOutBtn = By.xpath("//a[normalize-space()='View Cart and Checkout']");


    public void addTProduct1ToCart() {
        action.jsClickOnElement(addToCartProduct1Btn);
        action.click(addQtyBtn);
        waitUntilElementIsVisible(closeAddToCartBtn);
        action.click(closeAddToCartBtn);
    }

    public void addTProduct2ToCart() {
        action.jsClickOnElement(addToCartProduct2Btn);
        action.click(addQtyBtn);
        waitUntilElementIsEnabled(viewCartAndCheckOutBtn);
        action.click(viewCartAndCheckOutBtn);
    }

}
