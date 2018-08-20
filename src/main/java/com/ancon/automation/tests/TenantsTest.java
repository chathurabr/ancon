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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private TenantSummary tenantSummary;


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
       // extent.loadConfig(new File(System.getProperty("user.dir") + CommonClass.path + "utils\\extent-config.xml"));
    }

    @BeforeClass
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
        commonClass = new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);
        tenantSummary = new TenantSummary(driver);
        outlet = new Outlet(driver);

        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream(".\\Base.properties"));
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
        //    driver.close();
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
        tenantSummary.verifyTenantDetails(tenantname,"0");
    }

    @Test(description = "Create new Outlet", priority = 4, enabled = true)
    public void createnewOutlet() {
        /*enter Outlet Details*/
        outlet.createOutlet(outletName, outletBusinessNumber, "street1", "zip2", "city2", "+46784596321");
        /*Set Opening Hours*/
        outlet.nonAdvancedRoutineTime();
        /* change colors*/
        outlet.colorBox("29, 61, 145","249, 89, 25");
        outlet.changeColor("#008000","0, 128, 0","#008","0, 0, 136");
        outlet.colorBox("0, 128, 0","0, 0, 136");
        /* set advanced Routine Times*/
        outlet.advancedRoutineTimes();
        outlet.clickOntimebox(7,20);  // mon 6 - 29
        /*save*/
        outlet.saveCreateOutlet();
    }

    @Test(description = "Verify Created Outlet Details", priority = 5, enabled = true)
    public void verifyCreatedOutlet() {
        tenantSummary.verifyOutlet(outletName);
    }

    @Test(description = "Verify outlet details when clicking view button in tenants summary page", priority = 6, enabled = true)
    public void viewOutletDetails() {
        driver.navigate().refresh();
        tenantSummary.clickViewOutlet();
        outlet.verifyCreatedOutletDetails(outletName, outletBusinessNumber, "street1", "zip2", "city2", "+46784596321");
        outlet.navigateToTenantSummaryPage();
    }

    @Test(description = "Edit Created Tenant details", priority = 7, enabled = true)
    public void editCreatedTenant() {
        tenants.editTenant("Edit a Tenant");
        tenants.tenantDetails(tenantEdit + tenantname, tenantEdit + tenantBusinessNumber);
        tenants.editTenantAdmin(tenantEdit + tenantEmail, tenantEdit + tenantFirstName, tenantEdit + tenantLastName);
    }


    @Test(description = "Verify Created tenant Details in summary page", priority = 8, enabled = true)
    public void verifyEditedTenant() {
        tenantSummary.verifyTenantDetails(tenantEdit + tenantname,"1");
    }

    @Test(description = "Edit Created Outlet details", priority = 9, enabled = true)
    public void editCreatedOutlet() {
        outlet.editCreatedOutlet(outletName, "55" + outletBusinessNumber, "street11", "zip22", "city22", "+94784596321");
    }

    @Test(description = "Verify Created Outlet Details", priority = 10, enabled = true)
    public void verifyEditedOutlet() {
        tenantSummary.verifyOutlet("edited" + outletName);
    }

    // tenats new view
    @Test(description = "Verify outlet details when clicking edit button in tenants summary page", priority = 11, enabled = true)
    public void editOutletDetails() {
        driver.navigate().refresh();
        tenantSummary.clickEditOutlet();
        outlet.verifyEditedOutletDetails("edited"+outletName, "55" + outletBusinessNumber, "street11", "zip22", "city22", "+94784596321");
        outlet.navigateToTenantSummaryPage();
    }

    @Test(description = "Disable Cancel Tenant", priority = 12, enabled = true)
    public void disabledTenantCancel() {
        tenantSummary.disableTenantCancel();
    }

    @Test(description = "Disable anyway Tenant", priority = 13, enabled = true)
    public void disabledTenantAnyway() {
        tenantSummary.disableTenantAnyway();
        tenantSummary.verifyButtonChangeTenant();
    }


/*    @Test(description = "Set a custom opening time for outlet", priority = 12, enabled = false)
    public void setCustomOutletTime() {
        outlet.selectSingleOpeningTime(outletName);

    }*/

    @Test(description = "Disable anyway Outlet", priority = 14, enabled = true)
    public void disabledOutletCancel() {
        tenantSummary.disableOutletCancel();
    }

    @Test(description = "Disable Outlet", priority = 15, enabled = true)
    public void disabledOutletAnyway() {
        tenantSummary.disableOutletanyway();
        tenantSummary.verifyButtonChangeOutlet();
    }
    @Test(description = "Delete Outlet Cancel", priority = 16, enabled = true)
    public void cancelDeleteOutlet() {
        tenantSummary.deleteOutletCancel();
    }

    @Test(description = "Delete Outlet", priority = 17, enabled = true)
    public void deleteOutlet() {
        tenantSummary.deleteOutlet();
        tenantSummary.verifyOutletDelete();
    }

    @Test(description = "Delete Tenant Cancel", priority = 18, enabled = true)
    public void cancelDeleteTenant() {
        tenantSummary.deleteTenantCancel();
    }

    @Test(description = "Delete Tenant", priority = 19, enabled = true)
    public void deleteTenant() {
        tenantSummary.deleteTenant();
        tenantSummary.verifyTenantDelete();
    }
}
