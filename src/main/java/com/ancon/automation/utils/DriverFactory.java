package com.ancon.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by chathura on 03/07/2018.
 */

public class DriverFactory {
    private static String url;
    private static String browser;
    protected WebDriverWait wait;
    static WebDriver driver;

    public static WebDriver getDriver() {
        String filePath = System.getProperty("user.dir");
        // get data from property file
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("Base.properties"));
            url= properties.getProperty("URL");
            browser =  properties.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", filePath+"//src//main//java//com//ancon//automation//webDriver//geckodriver.exe");
            driver = new FirefoxDriver();
            System.out.println("Firefox Browser Opened");
        }

        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver", filePath +"/src/main/java//com/ancon/automation/webDriver/chromedriver.exe");
            driver = new ChromeDriver();
            System.out.println("Chrome Browser Opened");
        }

        else{
            //If no browser passed throw exception
            try {
                throw new Exception("Browser name is not correct");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }


}
