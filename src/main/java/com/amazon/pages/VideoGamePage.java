package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VideoGamePage extends BaseClass {
    Action action = new Action();
    By freeShippingFilter = By.xpath("//div[@id='s-refinements']//*[normalize-space()='Free Shipping']");
    By sortFeatureBtn = By.xpath("//span//*[contains(@name,'s')]");
    By sortFromHighToLow = By.xpath("//a[normalize-space()='Price: High to Low'] | //a[@id='s-result-sort-select_2'] ");
    By newFilter = By.xpath("//*[@id='filters']//span[text()='New']");
    By allPageProducts = By.xpath("//*[contains(@class, 'widgetId=search-results')]//span[contains(@class,'a-price-whole')]//ancestor:: div[@class='a-section']");
    By addToCartBtn = By.id("add-to-cart-button");
    By nextPage = By.xpath("//a[normalize-space()='Next']");
    public void filterByFreeShipping(){
        action.click(freeShippingFilter);
    }
    public void sortByPriceHighToLow(){
        action.jsClickOnElement(sortFeatureBtn);
        action.click(sortFromHighToLow);
    }
    public void filterByNew(){
        action.click(newFilter);
    }

    // Find all product elements on the page
    private List<WebElement> getPageProductsElements(){
        //return driver.findElements(By.xpath("//*[contains(@class, 'widgetId=search-results')]//span[contains(@class,'a-price-whole')]//ancestor:: div[@class='a-section']"));
        waitUntilElementIsVisible(allPageProducts);
        return action.getElements(allPageProducts);
    }

    private boolean checkPrice(int price){
        Boolean flag=false;
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)<price){
                flag=true;
                break;
            }
        }
        return flag;
    }
    // Find all product prices on the page
    private List<Integer> getPageProductsPrices(){
        List<Integer> prices = new ArrayList<Integer>();
        List<WebElement> productElements = getPageProductsElements();

        for (WebElement product : productElements){
            // Find the price element within the product element
            /*addition of a . at the beginning of the XPath expression,
             makes the search start from the current element
             rather than always finding the first priceElement on the page
             */
            WebElement priceElement = product.findElement(By.xpath(".//span[contains(@class,'a-price-whole')]"));

            // Extract the price as text and remove any commas or dots
            String priceText = priceElement.getText().replaceAll(",", "").replaceAll("\\.", "");

            // Parse the price as an integer
            int price = Integer.parseInt(priceText);
            prices.add(price);
        }
        return prices;
    }
    // Find all product names on the page
    public List<String> getPageProductsNames(){
        List<String> productNames = new ArrayList<String>();
        List<WebElement> productElements = getPageProductsElements();
        for (WebElement product : productElements){
            WebElement productName = product.findElement(By.xpath(".//h2//span"));
            String name=productName.getText();
            productNames.add(name);
        }
        return productNames;
    }
    //add to cart all products above a specific price
    public void addALLProductsToCartAbovePrice(int price){
        List<String> productNames = getPageProductsNames();
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)>price){
                addToCart(i);
            }
        }
    }
    //add to cart all products below a specific price
    public void addALLProductsToCartByBelowPrice(int price){
        while (true) {
            if (!checkPrice(price)) {
                action.click(nextPage);
            }
            else break;
        }
        List<String> productNames = getPageProductsNames();
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)<price){
                addToCart(i);
            }
        }
    }
    public List<String> getAllProductsNamesAbovePrice(int price){
        List<String> productNames = getPageProductsNames();
        List<String> names = new ArrayList<String>();
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)>price){
                System.out.println(productNames.get(i));
                System.out.println(prices.get(i));
                names.add(productNames.get(i));
            }
        }
        return names;

    }
    public List<String> getAllProductsNamesBelowPrice(int price){
        while (true) {
            if (!checkPrice(price)) {
                action.click(nextPage);
            }
            else break;
        }
        List<String> productNames = getPageProductsNames();
        List<String> names = new ArrayList<String>();
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)<price){
                System.out.println(productNames.get(i));
                System.out.println(prices.get(i));
                names.add(productNames.get(i));
            }
        }
        return names;

    }
    //open the selected index of page products in a new tab and add it to cart and return to the main tab
    private void addToCart(int index){
        WebElement productElement = getPageProductsElements().get(index).findElement(By.xpath(".//h2//a"));
        action.jsOpenLinkInNewTab(productElement.getAttribute("href"));
        ArrayList<String> tabs = getCurrentTabs();
        //switch to the product opened tab
        switchBetweenTabs(tabs,1);
        waitUntilElementIsEnabled(addToCartBtn);
        action.jsClickOnElement(addToCartBtn);
        closeCurrentPage();
        //return to the main tab
        switchBetweenTabs(tabs,0);

    }
}
