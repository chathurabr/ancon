package com.ancon.automation.tests;

import com.ancon.automation.pages.Outlet;
import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.Screenshot;
import com.ancon.automation.utils.DriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
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
    private Outlet outlet;

    private String email;
    private String password;
    private String tenantname;
    private String tenantBusinessNumber;
    private String tenantEmail;
    private String tenantFirstName;
    private String tenantLastName;
    private String outletName;
    private String outletBusinessNumber;
    private String tenantEdit = "Up";

    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeTest
    public void startReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + CommonClass.path + "/test-output/STMExtentReport.html", true);
        extent
                .addSystemInfo("Host Name", "Ancon")
                .addSystemInfo("Environment", "Ancon Automation Testing")
                .addSystemInfo("User Name", "Chathura");
        extent.loadConfig(new File(System.getProperty("user.dir") + CommonClass.path + "utils\\extent-config.xml"));
    }

    @BeforeClass
    public void SetUp() throws IOException {
        WebDriver driver = DriverFactory.getDriver();
        commonClass = new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);
        outlet = new Outlet(driver);

        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream(filePath + CommonClass.path + "\\utils\\Base.properties"));
        email = properties.getProperty("email");
        password = properties.getProperty("password");
        //Tenant Details
        tenantname = properties.getProperty("tenantname");
        tenantBusinessNumber = properties.getProperty("tenantBusinessNumber");
        tenantEmail = properties.getProperty("tenantEmail");
        tenantFirstName = properties.getProperty("tenantFirstName");
        tenantLastName = properties.getProperty("tenantLastName");
        //Outlet Details
        outletName = properties.getProperty("outletname");
        outletBusinessNumber = properties.getProperty("outletBusinessNumber");

        logger = extent.startTest("Ancon test");
    }

    @BeforeMethod(description = "wait for page load")
    public void waitForPageLoad() {
        CommonClass.waitForLoad();
    }

    @AfterMethod(description = "Taking ScreenShot for Failed Tests and Create Extent Report")
    public void getResult(ITestResult result) {
        Screenshot.screenShot(result); // take ScreenShot On Failure
        /*Result file*/
        if (result.getStatus() == ITestResult.FAILURE) {
           // logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is - " + result.getName() +"  - error : " +result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is - " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Case Passed - " + result.getName());
        }
        extent.endTest(logger);
    }

    @AfterTest
    public void endReport() {
        extent.flush();
        extent.close();
    }

    @Test(description = "login to the system with valid  Email and Password")
    public void loginTosystem() {
        login.loginToAncon(email, password);
    }

    @Test(description = "Go to tenant Page", priority = 1, enabled = true)
    public void tenantsTab() {
        commonClass.selectSidebarMenu("Tenants");
    }

    @Test(description = "Create new tenant", priority = 2, enabled = true)
    public void createTenant() {
        tenants.verifyPageElements();
        tenants.createNewTenant("Create a Tenant");
        tenants.verifyTenantErrorMessages();
        tenants.tenantDetails(tenantname, tenantBusinessNumber);
        tenants.tenantAdminDetails(tenantEmail, tenantFirstName, tenantLastName);
    }

    @Test(description = "Verify Created tenant Details in summary page", priority = 3, enabled = true)
    public void verifyCreatedTenant() {
        tenants.verifyTenantDetails(tenantname);
    }

    @Test(description = "Create new Outlet", priority = 4, enabled = true)
    public void createnewOutlet() {
        /*enter Outlet Details*/
        outlet.createOutlet(outletName, outletBusinessNumber, "steet1", "zip2", "city2", "0784596321");
        /*Set Opening Hours*/
        outlet.createOutletRoutineTme();
        /* change colors*/
        outlet.colorBox();
        outlet.saveCreateOutlet();
    }

    @Test(description = "Verify Created Outlet Details", priority = 5, enabled = true)
    public void verifyCreatedOutlet() {
        outlet.verifyOutlet(outletName);
    }

    @Test(description = "Edit Created Tenant details", priority = 6, enabled = true)
    public void editCreatedTenant() {
        tenants.editTenant("Edit a Tenant");
        tenants.tenantDetails(tenantEdit + tenantname, tenantEdit + tenantBusinessNumber);
        tenants.editTenatAdnim(tenantEdit+tenantEmail,tenantEdit+tenantFirstName,tenantEdit+tenantLastName);
        tenants.verifyTenantDetails(tenantEdit+tenantname);
    }

    @Test(description = "Edit Created Outlet details", priority = 7, enabled = true)
    public void editCreatedOutlet() {

    }


}
