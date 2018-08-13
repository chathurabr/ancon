package com.ancon.automation.tests;

import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.TenantSummary;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.utils.DriverFactory;
import com.ancon.automation.utils.Screenshot;
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

public class SearchTest {
    static WebDriver driver = null;
    private Login login;
    private Tenants tenants;
    private TenantSummary tenantSummary;
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
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
        login = new Login(driver);
        tenants = new Tenants(driver);
        tenantSummary = new TenantSummary(driver);
        commonClass = new CommonClass(driver);
        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream(filePath + CommonClass.path + "utils\\Base.properties"));
        email = properties.getProperty("email");
        password = properties.getProperty("password");
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

    @Test(description = "login to the system with valid  Email and Password")
    public void loginTosystem() {
        login.loginToAncon(email, password);
    }

    @Test(description = "Navigate to tenant page", priority = 1, enabled = true)
    public void navigateToTenant() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.verifyPageElements();
    }

    @Test(description = "Verify the placeholder text in search", priority = 2, enabled = true)
    public void verifySearchPlaceholderText() {
        tenantSummary.verifyPlaceholderText();
    }

    @Test(description = "search with a non-existing search term", priority = 3, enabled = true)
    public void searchNonExistingTenant() {
        tenantSummary.searchNonExistingTenant("invalid");
    }

    @Test(description = "search using an existing complete search term", priority = 4, enabled = true)
    public void searchTenantComplete() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchTenant("testname1234");
    }

    @Test(description = "search using a partial search term ", priority = 5, enabled = true)
    public void searchTenantPartial() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchTenant("name");
    }

    @Test(description = "search using letters as search terms", priority = 6, enabled = true)
    public void searchTenantUsingLetters() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchTenant("ab");
    }

    @Test(description = "search using a search term which results a single tenant", priority = 7, enabled = true)
    public void searchOnlySingleTenant() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchTenant("automation single");
    }

    @Test(description = "search using multiple search terms", priority = 8, enabled = true)
    public void searchMultipleTerms() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchTenant("tenant test");
    }

    @Test(description = "search Outlets ", priority = 9, enabled = true)
    public void searchOutlet() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchOutlet("automation outlet");
    }

    @Test(description = "search using enter key ", priority = 10, enabled = true)
    public void searchUsingEnterKey() {
        commonClass.selectSidebarMenu("Tenants");
        tenantSummary.searchFunctionWithEnterKey("testname1234");
    }

    @AfterTest
    public void endReport() {
        extent.flush();
        extent.close();
        driver.quit();
    }


}
