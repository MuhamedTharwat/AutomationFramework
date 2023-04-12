package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static Properties properties;
    public static WebDriverWait wait;

    public static void loadConfig() {
        properties = new Properties();
        System.out.println("Configurations Loaded !");
        try {
            FileInputStream inputStream = new FileInputStream("configurations/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchWeb() {
        if (properties.getProperty("browser").equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);
        } else if (properties.getProperty("browser").equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver(options);
        } else if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().
                implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("implicitWait"))));
        driver.get(properties.getProperty("url"));
    }

    public static void waitUntilElementIsVisible(By element){
        wait = new WebDriverWait(driver,Duration.ofSeconds(Long.parseLong(properties.getProperty("explicitWait"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public static void waitUntilElementIsEnabled(By element){
        wait = new WebDriverWait(driver,Duration.ofSeconds(Long.parseLong(properties.getProperty("explicitWait"))));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void refreshCurrentPage(){
        driver.navigate().refresh();
    }
    public static void closeCurrentPage(){driver.close();}
    public static ArrayList<String> getCurrentTabs(){
        ArrayList<String>  tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs;
    }
    public static void switchBetweenTabs(ArrayList<String> tabs,int tabIndex){
        driver.switchTo().window(tabs.get(tabIndex));
    }

}
