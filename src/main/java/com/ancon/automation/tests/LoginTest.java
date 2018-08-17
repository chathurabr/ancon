package com.ancon.automation.tests;

import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.utils.Screenshot;
import com.ancon.automation.utils.DriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by chathura on 03/07/2018.
 */
public class LoginTest {
    static WebDriver driver = null;
    private Login login;
    private CommonClass commonClass;

    private String email;
    private String password;

    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeTest
    public void startReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + CommonClass.path + "/reports/Ancon_Automation.html", true);
        extent
                .addSystemInfo("Host Name", "Ancon")
                .addSystemInfo("Environment", "Ancon Automation Testing")
                .addSystemInfo("User Name", "Chathura");
        extent.loadConfig(new File(System.getProperty("user.dir") + CommonClass.path + "utils\\extent-config.xml"));
    }

    @BeforeClass
    public void SetUp() {
        driver = DriverFactory.getDriver();
        login = new Login(driver);
        commonClass = new CommonClass(driver);

        // get data from property file
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("user.dir");
            properties.load(new FileInputStream(".\\Base.properties"));
            email = properties.getProperty("email");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod(description = "wait for page load and start logger test report")
    public void waitForPageLoad(Method method) {
        logger = extent.startTest(method.getName()); // start logger test report
        CommonClass.waitForLoad();
    }


    @Test(description = "Login to the system")
    public void loginTosystem() {
        login.loginToAncon(email, password);
    }


    @AfterMethod(description = "Taking ScreenShot for Failed Tests and Create Extent Report")
    public void getResult(ITestResult result) {
        Screenshot.screenShot(result); // take ScreenShot On Failure
        /*Result file*/
        if (result.getStatus() == ITestResult.FAILURE) {
            // logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName() +"  - error : " +result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Case Passed - " + result.getName());
        }
        extent.endTest(logger);

    }

    @AfterClass
    public void endReport() {
        extent.flush();
        extent.close();
        driver.quit();
    }

}

