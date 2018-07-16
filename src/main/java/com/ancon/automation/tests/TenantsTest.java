package com.ancon.automation.tests;

import com.ancon.automation.pages.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chathura on 11/07/2018.
 */
public class TenantsTest {
    private static WebDriver driver;
    private CommonClass commonClass;
    private Login login;
    private Tenants tenants;

    private String email;
    private String password;

    @BeforeClass
    public void SetUp() throws IOException {
        driver = DriverFactory.getDriver();
        commonClass= new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);

        // get data from property file
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir");
        properties.load(new FileInputStream( filePath + "\\src\\main\\java\\com\\ancon\\automation\\utils\\Base.properties"));
        email= properties.getProperty("email");
        password =  properties.getProperty("password");

    }

    @Test (description = "login to the system with valid  Email and Password")
    public void loginTosystem(){
        login.loginToAncon(email,password);
    }

    @Test(description = "Create new tenant", priority = 1)
    public void createTenant(){
        commonClass.selectSidebarMenu("Tenants");
        tenants.verifyPageElements();
        tenants.createNewTenant("Create a Tenant");
        tenants.tenantDetails("saman","123546","a@mail.com","nuwan","sameera");
        tenants.verifyCreatedTenant("saman");
    }

    @Test(description = "Verify Created tenant Details", priority = 2)
    public void verifyCreatedTenant(){
        tenants.verifyCreatedTenant("saman");
    }

    @Test(description = "Create new Outlet", priority = 3)
    public void createnewOutlet(){
        tenants.createOutlet("test name","test number");
    }

    @Test(description = "Verify Created Outlet Details", priority = 4)
    public void verifyCreatedOutlet(){
        tenants.verifyOutlet("test name");
    }


}
