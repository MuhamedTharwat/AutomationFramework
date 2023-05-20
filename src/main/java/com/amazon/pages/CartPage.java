package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseClass {
    Action action = new Action();
    By cartProducts = By.xpath("//div[@id='sc-active-cart']//div[contains(@id,'sc-active')]");

    public List<WebElement> getCartProductsElements() {
        return action.getElements(cartProducts);
    }

    public List<String> getCartProductsNames() {
        List<String> cartProductNames = new ArrayList<String>();
        List<WebElement> cartProductElements = getCartProductsElements();
        for (WebElement product : cartProductElements) {
            WebElement productName = product.findElement(By.xpath(".//span[contains(@class,'a-truncate-cut')]"));
            String name = productName.getText();
            cartProductNames.add(name);
        }
        return cartProductNames;
    }
}
