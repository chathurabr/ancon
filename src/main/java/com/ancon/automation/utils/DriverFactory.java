package com.ancon.automation.utils;

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
    protected WebDriverWait wait;
    public static WebDriver driver;

    public static WebDriver getDriver() {
        String filePath = System.getProperty("user.dir");
        // get data from property file
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath + "\\src\\main\\java\\com\\ancon\\automation\\utils\\Base.properties"));
            url= properties.getProperty("URL");

        } catch (IOException e) {
            e.printStackTrace();
        }


        // set chrome web driver
    //    WebDriver driver;
     //   System.setProperty("webdriver.chrome.driver", filePath + "\\src\\main\\java\\com\\ancon\\automation\\webDriver\\chromedriver.exe");
      //  driver = new ChromeDriver();

        // set Firefox web driver
        System.setProperty("webdriver.gecko.driver", filePath +"\\src\\main\\java\\com\\ancon\\automation\\webDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();



        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        chromeCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        driver.get(url);
        return driver;
    }


}
