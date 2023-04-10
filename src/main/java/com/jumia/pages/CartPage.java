package com.jumia.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;

public class CartPage extends BaseClass {
    Action action = new Action();
    By product1Qty = By.xpath("//div[@class='col12']//article//article[1]//form//span");
    By product2Qty = By.xpath("//div[@class='col12']//article//article[2]//form//span");
    By product1Price = By.xpath("//div[@class='col12']//article//article[1]//div[@class='prc']");
    By product2Price = By.xpath("//div[@class='col12']//article//article[2]//div[@class='prc']");
    By subTotalPrice = By.xpath("//div[@class='col4']//article//p[contains(text(),'EGP')]");

    public float calculateProduct1TotalPrice(){
        String qty1= action.getElementText(product1Qty);
        float product1Qty = Float.parseFloat(qty1);
        String price1= action.getElementText(product1Price);
        float product1UnitPrice = Float.parseFloat(price1.substring(4));
        float product1ToTalPrice = product1Qty*product1UnitPrice;
        return product1ToTalPrice;

//        String q2= action.getElementText(product2Qty);
//        float x1 = Float.parseFloat(q2);
//        String p2= action.getElementText(product2Price);
//        float x2 = Float.parseFloat(p2.substring(4));
//
//        float po1 = y1*y2;
//        float po2 = x1*x2;
//        float s = po1+po2;
//
//        String sub = action.getElementText(subTotalPrice);
//        float t = Float.parseFloat(sub.substring(4));

    }
    public float calculateProduct2TotalPrice() {
        String qty2 = action.getElementText(product2Qty);
        float product2Qty = Float.parseFloat(qty2);
        String price2 = action.getElementText(product2Price);
        float product2UnitPrice = Float.parseFloat(price2.substring(4));
        float product2ToTalPrice = product2Qty * product2UnitPrice;
        return product2ToTalPrice;
    }
    public String calculateProductsTotalPrice(){
        driver.navigate().refresh();
        return String.valueOf(calculateProduct1TotalPrice()+calculateProduct2TotalPrice());
    }
    public String subTotalPrice() {
        String sub = action.getElementText(subTotalPrice);
        float subTotalPrice=Float.parseFloat(sub.substring(4));
        return String.valueOf(subTotalPrice);
    }

}
