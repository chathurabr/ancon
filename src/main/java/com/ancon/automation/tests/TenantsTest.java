package com.ancon.automation.tests;

import com.ancon.automation.pages.Outlet;
import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.Screenshot;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
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

    @BeforeClass
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
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
    }

    @BeforeMethod(description = "wait for page load")
    public void waitForPageLoad() {
        CommonClass.waitForLoad();
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
        //  tenants.editTenatAdnim(tenantEdit+tenantEmail,tenantEdit+tenantFirstName,tenantEdit+tenantLastName);
        //   tenants.verifyTenantDetails(tenantEdit+tenantname);
    }

    @AfterMethod(description = "Taking ScreenShot for Failed Tests")
    public void takeScreenShotOnFailure(ITestResult testResult) {
        Screenshot.screenShot(testResult);
    }

    @AfterClass
    public void endReport() {
        driver.close();
    }

}
