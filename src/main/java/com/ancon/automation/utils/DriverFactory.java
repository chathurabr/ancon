package com.ancon.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chathura on 03/07/2018.
 */

public class DriverFactory {
    private static String url;

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


        // set web driver

        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", filePath + "\\src\\main\\java\\com\\ancon\\automation\\webDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        chromeCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        driver.get(url);
        return driver;
    }


}
