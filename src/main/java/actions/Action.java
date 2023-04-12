package actions;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Action extends BaseClass {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public void type(By element, String text){
        driver.findElement(element).sendKeys(text);
    }
    public void click(By element){
        driver.findElement(element).click();
    }
    public List<WebElement> getElements(By element){
        return driver.findElements(element);
    }
    public String getElementText(By element){
        return driver.findElement(element).getText();
    }
    public boolean findElement(By element) {
        boolean flag = false;
        try {
            driver.findElement(element).isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println("Successfully Found element");

            } else {
                System.out.println("Unable to locate element");
            }
        }
        return flag;
    }
    public void selectByValue(By element, String value) {
        try {
            Select s = new Select(driver.findElement(element));
            s.selectByValue(value);
            System.out.println("Option selected by Index");

        } catch (Exception e) {
            System.out.println("Option not selected by Index");
            e.printStackTrace();
        }

    }
    public void selectCheckBox(By element) {
        boolean flag = false;
        flag = findElement(element);
        if (flag) {
            flag = driver.findElement(element).isSelected();
            if (flag) {
                System.out.println("Check box is already Selected");
            } else {
                driver.findElement(element).click();
                System.out.println("Check is Selected");
            }
        } else {
            System.out.println("Unable to locate Check box");
        }
    }

    public void hoverOnElement(By element) {
        waitUntilElementIsVisible(element);
        new Actions(driver).moveToElement(driver.findElement(element)).build().perform();
    }

    public void jsHoverOnElement(By element) {
        WebElement webElement = driver.findElement(element);
        String javaScript = "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "arguments[0].dispatchEvent(evObj);";
        js.executeScript(javaScript, webElement);
    }


    public void jsScrollToElement(By element) {
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void jsClickOnElement(By element) {
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].click();", webElement);
    }

    public void jsOpenLinkInNewTab(String link){
        String newTabScript = "window.open('" + link + "','_blank');";
        js.executeScript(newTabScript);
    }


}
