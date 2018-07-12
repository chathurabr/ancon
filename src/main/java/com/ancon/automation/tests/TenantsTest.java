package com.ancon.automation.tests;

import com.ancon.automation.pages.CommonClass;
import com.ancon.automation.pages.Login;
import com.ancon.automation.pages.Tenants;
import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by chathura on 11/07/2018.
 */
public class TenantsTest {
    static WebDriver driver = null;
    CommonClass commonClass;
    Login login;
    Tenants tenants;

    @BeforeClass
    public void SetUp() {
        driver = DriverFactory.getDriver();
        commonClass= new CommonClass(driver);
        login = new Login(driver);
        tenants = new Tenants(driver);
    }

    @Test (description = "User login with entering Email Password")
    public void loginTosystem(){
        login.loginToAncon();
    }

    @Test(description = "User go to tenant page", priority = 1)
    public void tenant(){
        commonClass.selectSidebarMenu("Tenants");
        tenants.createNewTenant("Create a Tenant");
        tenants.tenantDetails("saman","123546","a@mail.com","nuwan","sameera");


    }


}
