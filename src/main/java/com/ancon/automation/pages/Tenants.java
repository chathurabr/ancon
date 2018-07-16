package com.ancon.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by chathura on 11/07/2018.
 */
public class Tenants {
    WebDriver driver ;
    WebDriverWait wait;
    CommonClass commonClass;

    By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    By txt_name = By.xpath("//input[@name='name']");
    By txt_businessNumber = By.xpath("//input[@name='businessNumber']");
    By btn_logoUpload = By.xpath("//button[contains(text(),'Upload')]");

    By txt_email = By.xpath("//input[@name='email']");
    By txt_firstName = By.xpath("//input[@name='firstName']");
    By txt_lastName = By.xpath("//input[@name='lastName']");
    By btn_save = By.xpath("//button[contains(text(),'Save')]");
    By btn_Discard= By.xpath("//button[contains(text(),'Discard')]");
    By btn_saveNtenant = By.xpath("//button[contains(text(),'Save & View Tenant')]");
    By input_search = By.xpath("//input[@placeholder='Search Tenants or Outlets']");
    By txt_OrderNumber = By.xpath("//tbody[1]/tr[1]/td[1][@class='id-td id-content___ouR2G']");   //tbody[1]/tr[1]/td[1]

    By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
    By btn_Edit_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[2]/button/span[contains(text(),'Edit')]");
    By btn_Disable_1 = By.xpath("//tbody[1]/tr/td[7]/button/span[contains(text(),'Disable')]");
    By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
    By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");

    By btn_CreateOutlet = By.xpath("//button[contains(text(),'Create Outlet')]");
    // Create Outlet
    By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
    By txt_outletName = By.xpath("//input[@name='outletName']");
    By txt_otletBusinessNumber = By.xpath("//input[@name='outletBusinessNumber']");
    By btn_BackFromOutlet = By.xpath("//button/i[@class='a_icon-arrow_back']");

    public Tenants(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void verifyPageElements(){
        WebElement searchArea = driver.findElement(input_search);
        Assert.assertEquals(true, searchArea.isDisplayed());
        System.out.println("Search bar is available");
        WebElement btn_CreateNew = driver.findElement(input_search);
        Assert.assertEquals(true, searchArea.isDisplayed());
        System.out.println("Create New button is available");
    }

    public void createNewTenant(String hederName){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateNew))).click();
        org.testng.Assert.assertEquals(CommonClass.getPageName(),hederName);
        System.out.println("Create a Tenant label is available");
    }

    public void tenantDetails(String name, String businessNumber, String email, String firstName, String lastName){
        System.out.println("-Filling Tenant Details-");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).sendKeys(name);
        System.out.println("Tenant Name :"+name);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_businessNumber))).sendKeys(businessNumber);
        System.out.println("business number :"+businessNumber);
     //   CommonClass.sleepTime(2000);
     //   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_logoUpload))).click();
    /*    WebElement element = driver.findElement(btn_logoUpload);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        System.out.println("button clicked");*/
        String currentDir = System.getProperty("user.dir");
        driver.findElement(btn_logoUpload).sendKeys("C:\\Users\\chathurar\\Desktop\\New folder\\alignmet.jpg");

        //Tenant admin details
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_email))).sendKeys(email);
        System.out.println("Email * :"+email);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_firstName))).sendKeys(firstName);
        System.out.println("First Name :"+firstName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_lastName))).sendKeys(lastName);
        System.out.println("TLast name :"+lastName);
        //click on save button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Created a Tenant");
    }

    public void verifyCreatedTenant(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        String orderNumber = driver.findElement(txt_OrderNumber).getText();
        Assert.assertEquals(orderNumber,"001");
        System.out.println("Sequence order number '001' is available");
        String tname = driver.findElement(By.xpath("//table/tbody[1]/tr/td[3]/div/span")).getText();
        Assert.assertEquals(tname,name);
        System.out.println("Created tenat name verified : "+ name);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr/td[4]")).getText();
        Assert.assertEquals(tcdate,dtf.format(now));
        System.out.println("Tenant Created date verified : "+tcdate);

        // verify View button available
        WebElement btn_view = driver.findElement(btn_View_1);
        Assert.assertEquals(true, btn_view.isDisplayed());
        System.out.println("View button is available");
        //verify edit button available
        WebElement btn_edit = driver.findElement(btn_Edit_1);
        Assert.assertEquals(true, btn_edit.isDisplayed());
        System.out.println("Edit button is available");
        //verify Disable button available
        WebElement btn_desable = driver.findElement(btn_Disable_1);
        Assert.assertEquals(true, btn_desable.isDisplayed());
        System.out.println("Disable button is available");
    }

    public void createOutlet(String outletName,String outletNumber){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_View_1))).click();
        System.out.println("Clicked on View button");
        Assert.assertEquals(CommonClass.getPageName(),"Tenant Details");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateOutlet))).click();
        System.out.println("Create Outlet button clicked");
        Assert.assertEquals(CommonClass.getPageName(),"Create an Outlet");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletName))).sendKeys(outletName);
        System.out.println("Outlet Name :"+outletName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_otletBusinessNumber))).sendKeys(outletNumber);
        System.out.println("Outlet business number :"+outletNumber);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Created an Outlet");
        CommonClass.sleepTime(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_BackFromOutlet));
        WebElement backButton = driver.findElement(btn_BackFromOutlet);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", backButton);
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
/*        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", backButton);*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        Assert.assertEquals(CommonClass.getPageName(),"Tenants");
        System.out.println("Back to Tenants Summary page successfully ");
    }

    public void verifyOutlet(String OutletName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(txt_outletNeme));
        String Outletname = driver.findElement(txt_outletNeme).getText();
        Assert.assertEquals(Outletname,OutletName);
        System.out.println("Created Outlet name verified : "+ OutletName);
        WebElement outlets = driver.findElement(lbl_Outlets);
        String numberOfOutlets = wait.until(ExpectedConditions.elementToBeClickable(outlets)).getText();
        Assert.assertEquals(numberOfOutlets,"1");
        System.out.println("Verified Number Of Outlets : "+numberOfOutlets);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[4]")).getText();
        Assert.assertEquals(tcdate,dtf.format(now));
        System.out.println("Outlet Created date verified : "+tcdate);
    }
}
