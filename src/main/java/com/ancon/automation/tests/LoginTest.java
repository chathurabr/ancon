package com.ancon.automation.tests;

import com.ancon.automation.pages.Dashboard;
import com.ancon.automation.pages.Login;
import com.ancon.automation.utils.CommonScreenshot;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by chathura on 03/07/2018.
 */
public class LoginTest {
    static WebDriver driver = null;
    Dashboard dashboard;
    Login login;


    @BeforeClass
    public void SetUp() {
        driver = DriverFactory.getDriver();
        dashboard = new Dashboard(driver);
        login = new Login(driver);
    }

    @Test (description = "User login with entering Email Password", priority = 0)
    public void loginTosystem(){
        login.loginToAncon();
    }
 //   @Test
 //   public void dashBoardTest(){
  //      dashboard.DashboardTest();
  //  }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        driver = CommonScreenshot.takeSnapshot(testResult);
    }


}

