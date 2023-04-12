package com.amazon.pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseClass {
    public List<WebElement> getCartProductsElements(){
        return driver.findElements(By.xpath("//div[@id='sc-active-cart']//div[contains(@id,'sc-active')]"));
    }
    public List<String> getCartProductsNames(){
        List<String> cartProductNames = new ArrayList<String>();
        List<WebElement> cartProductElements = getCartProductsElements();
        for (WebElement product : cartProductElements){
            WebElement productName = product.findElement(By.xpath(".//span[contains(@class,'a-truncate-cut')]"));
            String name=productName.getText();
            cartProductNames.add(name);
        }
        return cartProductNames;
    }
}
