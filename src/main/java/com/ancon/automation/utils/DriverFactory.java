package com.ancon.automation.utils;

import com.ancon.automation.pages.CommonClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
            properties.load(new FileInputStream(filePath + CommonClass.path+"utils\\Base.properties"));
            url= properties.getProperty("URL");
            browser =  properties.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            System.setProperty("webdriver.gecko.driver", filePath +CommonClass.path+"webDriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver", filePath + CommonClass.path+"webDriver\\chromedriver.exe");
            //create chrome instance
            driver = new ChromeDriver();
            DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
            chromeCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
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
        return driver;
    }


}
