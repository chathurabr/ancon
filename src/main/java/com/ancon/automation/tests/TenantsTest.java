package com.ancon.automation.tests;

import com.ancon.automation.pages.Outlet;
import com.ancon.automation.pages.TenantSummary;
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
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by chathura on 11/07/2018.
 */
public class TenantsTest {
    static WebDriver driver = null;
    private CommonClass commonClass;
    private Login login;
    private Tenants tenants;
    private Outlet outlet;
<<<<<<< Updated upstream
    private TenantSummary tenantSummary;
=======
    private TenantSummary tenantsummary;
>>>>>>> Stashed changes

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
        extent = new ExtentReports(System.getProperty("user.dir") + CommonClass.path + "/reports/Ancon_Automation.html", true);
        extent
                .addSystemInfo("Host Name", "Ancon")
                .addSystemInfo("Environment", "Ancon Automation Testing")
                .addSystemInfo("User Name", "Chathura");
        extent.loadConfig(new File(System.getProperty("user.dir") + CommonClass.path + "utils\\extent-config.xml"));
    }

    @BeforeClass
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
        commonClass = new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);
        tenantsummary = new TenantSummary(driver);
        outlet = new Outlet(driver);
        tenantSummary = new TenantSummary(driver);

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
    }

    @BeforeMethod(description = "wait for page load and start logger test report ")
    public void waitForPageLoad(Method method) {
        logger = extent.startTest(method.getName()); // start logger test report
        CommonClass.waitForLoad();
    }

    @AfterMethod(description = "Taking ScreenShot for Failed Tests and Create Extent Report")
    public void getResult(ITestResult result) {
        Screenshot.screenShot(result); // take ScreenShot On Failure
        /*Result file*/
        if (result.getStatus() == ITestResult.FAILURE) {
            // logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is - " + result.getName() + "  - error : " + result.getThrowable());
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
        driver.close();
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
        tenantSummary.verifyTenantDetails(tenantname);
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
        tenantSummary.verifyOutlet(outletName);
    }

    @Test(description = "Edit Created Tenant details", priority = 6, enabled = true)
    public void editCreatedTenant() {
        tenants.editTenant("Edit a Tenant");
        tenants.tenantDetails(tenantEdit + tenantname, tenantEdit + tenantBusinessNumber);
        tenants.editTenatAdnim(tenantEdit + tenantEmail, tenantEdit + tenantFirstName, tenantEdit + tenantLastName);
    }

<<<<<<< Updated upstream
    @Test(description = "Verify Created tenant Details in summary page", priority = 7, enabled = true)
    public void verifyEditedTenant() {
        tenantSummary.verifyTenantDetails(tenantEdit + tenantname);
    }

    @Test(description = "Edit Created Outlet details", priority = 8, enabled = true)
    public void editCreatedOutlet() {
        outlet.editCreatedOutlet(outletName, "55" + outletBusinessNumber, "steet11", "zip22", "city22", "+94784596321");
=======
    @Test(description = "Disable Cancel Tenant", priority = 7,enabled = true)
    public void disabledTenantCancel(){
        tenantsummary. disableTenantCancel();
    }

    @Test(description = "Disable anyway Tenant", priority = 8,enabled = true)
    public void disabledTenantAnyway(){
        tenantsummary. disableTenantAnyway();
        tenantsummary. verifyButtonChangeTenant();
    }

    @Test(description = "Disable anyway Outlet", priority = 9,enabled = true)
    public void disabledOutletCancel(){
        tenantsummary. disableOutletcancel();
    }

    @Test(description = "Disable anyway Outlet", priority = 10,enabled = true)
    public void disabledOutletAnyway(){
        tenantsummary. disableOutletanyway();
        tenantsummary. verifyButtonChangeOutlet();
    }
    @AfterMethod(description = "Taking ScreenShot for Failed Tests")
    public void takeScreenShotOnFailure(ITestResult testResult) {
        Screenshot.screenShot(testResult);
    }

    @AfterClass
    public void endReport() {
     //   driver.close();
>>>>>>> Stashed changes
    }

    @Test(description = "Verify Created Outlet Details", priority = 9, enabled = true)
    public void verifyEditedOutlet() {
        tenantSummary.verifyOutlet("etited" + outletName);
    }
}
