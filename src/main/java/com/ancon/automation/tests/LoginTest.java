package com.ancon.automation.tests;

import com.ancon.automation.pages.CommonClass;
import com.ancon.automation.pages.Dashboard;
import com.ancon.automation.pages.Login;
import com.ancon.automation.utils.CommonScreenshot;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chathura on 03/07/2018.
 */
public class LoginTest {
    static WebDriver driver = null;
    private Dashboard dashboard;
    private Login login;
    private CommonClass commonClass;

    private String email;
    private String password;

    @BeforeClass
    public void SetUp() {
        driver = DriverFactory.getDriver();
        dashboard = new Dashboard(driver);
        login = new Login(driver);
        commonClass= new CommonClass(driver);

        // get data from property file
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("user.dir");
            properties.load(new FileInputStream(filePath + "\\src\\main\\java\\com\\ancon\\automation\\utils\\Base.properties"));
            email= properties.getProperty("email");
            password =  properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test (description = "User login with entering Email Password", priority = 0)
    public void loginTosystem(){
        login.loginToAncon(email,password);
    }

/*    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        driver = CommonScreenshot.takeSnapshot(testResult);
    }*/

}

