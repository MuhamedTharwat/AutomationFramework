package com.amazon.pages;

import actions.Action;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VideoGamePage extends BaseClass {
    Action action = new Action();
    By freeShippingFilter = By.xpath("//div[@id='s-refinements']//*[normalize-space()='Free Shipping']");
    By sortFeatureBtn = By.xpath("//span[@id='a-autoid-0-announce']");
    By sortFromHighToLow = By.xpath("//a[normalize-space()='Price: High to Low'] | //a[@id='s-result-sort-select_2'] ");

    public void filterByFreeShipping(){
        action.click(freeShippingFilter);
    }
    public void sortByPriceHighToLow(){
        action.jsClickOnElement(sortFeatureBtn);
        action.click(sortFromHighToLow);
    }
    public void filterByCost(){
        // Find all product elements on the page
        List<WebElement> productElements = driver.findElements(By.xpath("//*[contains(@class, 'widgetId=search-results')]//span[contains(@class,'a-price-whole')]//ancestor:: div[@class='a-section']"));

        // Create a list to store products that meet the criteria
        List<String> cheapProducts = new ArrayList<>();

        // Loop through all product elements and extract the price of each one
        for (WebElement product : productElements) {

            /*addition of a . at the beginning of the XPath expression,
             makes the search start from the current element
             rather than always finding the first priceElement on the page
             */

            // Find the price element within the product element
            WebElement priceElement = product.findElement(By.xpath(".//span[contains(@class,'a-price-whole')]"));

            // Extract the price as text and remove any commas or dots
            String priceText = priceElement.getText().replaceAll(",", "").replaceAll("\\.", "");

            // Parse the price as an integer
            int price = Integer.parseInt(priceText);

            // Check if the price is below 15k EGP
            if (price < 15000) {
                // Extract the product title and add it to the list of cheap products
                WebElement titleElement = product.findElement(By.xpath(".//h2//span"));
                String x=titleElement.getText();
                System.out.println(x);
            }
        }


    }
    public List<WebElement> getPageProductsElements(){
        return driver.findElements(By.xpath("//*[contains(@class, 'widgetId=search-results')]//span[contains(@class,'a-price-whole')]//ancestor:: div[@class='a-section']"));
    }
    public List<Integer> getPageProductsPrices(){
        List<Integer> prices = new ArrayList<Integer>();
        List<WebElement> productElements = getPageProductsElements();

        for (WebElement product : productElements){
            // Find the price element within the product element
            WebElement priceElement = product.findElement(By.xpath(".//span[contains(@class,'a-price-whole')]"));

            // Extract the price as text and remove any commas or dots
            String priceText = priceElement.getText().replaceAll(",", "").replaceAll("\\.", "");

            // Parse the price as an integer
            int price = Integer.parseInt(priceText);
            prices.add(price);
        }
        return prices;
    }
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
    public void test(){
        List<String> productNames = getPageProductsNames();
        List<String> names = new ArrayList<String>();
        List<Integer> prices = getPageProductsPrices();
        for(int i=0 ; i<prices.size();i++){
            if(prices.get(i)<15000){
                System.out.println(productNames.get(i));
                System.out.println(prices.get(i));
                names.add(productNames.get(i));
                addToCart(i);
            }
        }

    }

    public void addToCart(int index){
        WebElement productElement = getPageProductsElements().get(index).findElement(By.xpath(".//h2//a"));
        action.jsOpenLinkInNewTab(productElement.getAttribute("href"));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("add-to-cart-button")).click();
        closeCurrentPage();
        driver.switchTo().window(tabs.get(0));

    }
}
