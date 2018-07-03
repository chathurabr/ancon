package com.ancon.automation.tests;

import com.ancon.automation.utils.DriverFactory;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;


/**
 * Created by nilenth on 10/24/2017.
 */
public class LoginTest {
    static WebDriver driver = null;


    @Test
    public void SetUp() {
        driver = DriverFactory.getDriver();
    }

  /*  @Test(description = "User login withInvalidEmail", priority = 0)
    public void invalidLoginTest() {
        login.invalidLogin();
    }

    @Test(description = "User login Sucessfully", priority = 2)
    public void successLogin() {
        login.login();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }*/


}

