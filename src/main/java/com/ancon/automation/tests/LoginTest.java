package com.ancon.automation.tests;

import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by chathura on 03/07/2018.
 */
public class LoginTest {
    static WebDriver driver = null;


    @Test
    public void SetUp() {
        driver = DriverFactory.getDriver();
    }



}

