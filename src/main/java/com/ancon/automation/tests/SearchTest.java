package com.ancon.automation.tests;

import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.CommonClass;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SearchTest {
    static WebDriver driver = null;
    private Login login;
    private Tenants tenants;
    private CommonClass commonClass;

    private String email;
    private String password;

    @BeforeSuite
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
        login = new Login(driver);
        tenants = new Tenants(driver);
        commonClass = new CommonClass(driver);
        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream(filePath + CommonClass.path + "utils\\Base.properties"));
        email = properties.getProperty("email");
        password = properties.getProperty("password");
    }

    @Test(description = "login to the system with valid  Email and Password")
    public void loginTosystem() {
        login.loginToAncon(email, password);
    }

    @Test(description = "Navigate to tenant page", priority = 1, enabled = true)
    public void NavigateToTenant() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.verifyPageElements();
    }

    @Test(description = "search with a non-existing search term", priority = 2, enabled = true)
    public void SearchNonExistingTenant() {
        tenants.searchNonExistingTenant("invalid");
    }

    @Test(description = "search using an existing complete search term", priority = 3, enabled = true)
    public void SearchTenantComplete() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.searchTenant("testname1234");
    }

    @Test(description = "search using a partial search term ", priority = 4, enabled = true)
    public void SearchTenantPartial() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.searchTenant("name");
    }

    @Test(description = "search using letters as search terms", priority = 5, enabled = true)
    public void SearchTenantUsingLetters() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.searchTenant("ab");
    }

    @Test(description = "search using a search term which results a single tenant", priority = 6, enabled = true)
    public void SearchOnlySingleTenant() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.searchTenant("one");
    }

    @Test(description = "search using multiple search terms", priority = 7, enabled = true)
    public void SearchMultipleTerms() {
        commonClass.selectSidebarMenu("Tenants");
        tenants.searchTenant("tenant test");
    }

}