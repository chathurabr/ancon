package com.ancon.automation.tests;

import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.Screenshot;
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
 * Created by chathura on 11/07/2018.
 */
public class TenantsTest {
    private CommonClass commonClass;
    private Login login;
    private Tenants tenants;

    private String email;
    private String password;
    private String tenantname;
    private String tenantBusinessNumber;
    private String tenantEmail;
    private String tenantFirstName;
    private String tenantLastName;
    private String outletname;
    private String outletBusinessNumber;

    @BeforeClass
    public void SetUp() throws IOException {
        WebDriver driver = DriverFactory.getDriver();
        commonClass= new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);

        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream( filePath + CommonClass.path+"\\utils\\Base.properties"));
        email= properties.getProperty("email");
        password =  properties.getProperty("password");
        //Tenant Details
        tenantname= properties.getProperty("tenantname");
        tenantBusinessNumber =  properties.getProperty("tenantBusinessNumber");
        tenantEmail= properties.getProperty("tenantEmail");
        tenantFirstName =  properties.getProperty("tenantFirstName");
        tenantLastName= properties.getProperty("tenantLastName");
        //Outlet Details
        outletname= properties.getProperty("outletname");
        outletBusinessNumber =  properties.getProperty("outletBusinessNumber");
    }

    @Test (description = "login to the system with valid  Email and Password")
    public void loginTosystem(){
        login.loginToAncon(email,password);
    }

    @Test(description = "Create new tenant", priority = 1,enabled = true)
    public void createTenant(){
        commonClass.selectSidebarMenu("Tenants");
        tenants.verifyPageElements();
        tenants.createNewTenant("Create a Tenant");
        tenants.verifyTenantErrorMessages();
        tenants.tenantDetails(tenantname,tenantBusinessNumber,tenantEmail,tenantFirstName,tenantLastName);
        tenants.verifyCreatedTenant(tenantname);
    }

    @Test(description = "Verify Created tenant Details", priority = 2,enabled = true)
    public void verifyCreatedTenant(){
        tenants.verifyCreatedTenant(tenantname);
    }

    @Test(description = "Create new Outlet", priority = 3,enabled = true)
    public void createnewOutlet() {
        // enter Outlet Details
        tenants.createOutlet(outletname, outletBusinessNumber, "steet1", "zip2", "city2", "0784596321");
        //Set Opening Hours
//        tenants.createOutletRoutineTme();
        // change colors
    //    tenants.colorBox();
        tenants.saveCreateOutlet();
    }

    @Test(description = "Verify Created Outlet Details", priority = 5,enabled = false)
    public void verifyCreatedOutlet(){
        tenants.verifyOutlet(outletname);
    }

    @AfterMethod(description = "Taking ScreenShot for Failed Tests",enabled = false)
    public void takeScreenShotOnFailure(ITestResult testResult) {
        Screenshot.screenShot(testResult);
    }

}