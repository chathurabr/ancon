package com.ancon.automation.tests;

import com.ancon.automation.pages.Dashboard;
import com.ancon.automation.utils.CommonScreenshot;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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


    @BeforeClass
    public void SetUp() {
        driver = DriverFactory.getDriver();
        dashboard = new Dashboard(driver);
    }

    @Test
    public void dashBoardTest(){
        dashboard.DashboardTest();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        driver = CommonScreenshot.takeSnapshot(testResult);
    }


}

