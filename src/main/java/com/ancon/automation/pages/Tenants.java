package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by chathura on 11/07/2018.
 */
public class Tenants extends CommonClass {
    private WebDriver driver;
    private WebDriverWait wait;

    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By txt_name = By.xpath("//input[@name='tenantName']");
    private By txt_businessNumber = By.xpath("//input[@name='businessNumber']");
    private By btn_logoUpload = By.xpath("//button[contains(text(),'Upload')]");
    //Create tenant
    private By txt_email = By.xpath("//input[@name='email']");
    private By txt_firstName = By.xpath("//input[@name='firstName']");
    private By txt_lastName = By.xpath("//input[@name='lastName']");
    private By txt_newEmail = By.xpath("//input[@name='newEmail']");
    private By txt_newfirstName = By.xpath("//input[@name='newFirstName']");
    private By txt_newlastName = By.xpath("//input[@name='newLastName']");
    private By btn_Add = By.xpath("//BUTTON[@type='submit'][text()='Add']");
    private By btn_save = By.xpath("//button[contains(text(),'Save')]");
    private By btn_Discard = By.xpath("//button[contains(text(),'Discard')]");
    private By btn_saveNtenant = By.xpath("//button[contains(text(),'Save & View Tenant')]");
    private By input_search = By.xpath("//input[@placeholder='Search Tenants or Outlets']");
    private By txt_OrderNumber = By.xpath("//td[@class='id-td id-content___3cuQ3'][text()='001']");

    private By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
    private By btn_Edit_1 = By.xpath("(//SPAN[text()='Edit'])[1]");
    private By btn_Disable_1 = By.xpath("//tbody[1]/tr/td[7]/button/span[contains(text(),'Disable')]");
    private By lbl_Error_Name = By.xpath("//form/div[1]/fieldset/div/div[1]/div[1]/label");
    private By lbl_Error_email = By.xpath("//form/div[2]/fieldset/div/div[1]/div[1]/label");
    private By btn_ChangeAdmin = By.xpath("//button[text()='Change Admin']");


    public Tenants(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void verifyPageElements() {
        WebElement searchArea = driver.findElement(input_search);
        Assert.assertTrue(searchArea.isDisplayed());
        System.out.println("Search bar is available");
        WebElement btn_CreateNe = driver.findElement(btn_CreateNew);
        Assert.assertTrue(btn_CreateNe.isDisplayed());
        System.out.println("Create New button is available");
    }

    public void createNewTenant(String hederName) {
       // sleepTime(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateNew))).click();
        Assert.assertEquals(getPageName(), hederName);
        System.out.println("successfully opened the 'Create a Tenant' page");
    }

    public void editTenant(String hederName) {
       // sleepTime(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_Edit_1));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Edit_1))).click();
        Assert.assertEquals(getPageName(), hederName);
        System.out.println("successfully opened the 'Edit a Tenant' page");
    }

    public void tenantDetails(String name, String businessNumber) {
        System.out.println("-Filling Tenant Details-");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).sendKeys(name);
        System.out.println("Tenant Name :" + name);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_businessNumber))).sendKeys(businessNumber);
        System.out.println("business number :" + businessNumber);
    }

    /*upload the image*/
    public void logoUpload() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_logoUpload))).click();
    /*   WebElement element = driver.findElement(btn_logoUpload);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        System.out.println("button clicked");*/
        //    String currentDir = System.getProperty("user.dir");
        //    driver.findElement(btn_logoUpload).sendKeys("C:\\Users\\chathurar\\Desktop\\New folder\\alignmet.jpg");
    }

    public void tenantAdminDetails(String email, String firstName, String lastName) {
        //Tenant admin details
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_email))).sendKeys(email);
        System.out.println("Email : " + email);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_firstName))).sendKeys(firstName);
        System.out.println("First Name :" + firstName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_lastName))).sendKeys(lastName);
        System.out.println("TLast name :" + lastName);
        //click on save button
        scrollIntoView(driver.findElement(btn_save));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Created a Tenant");
    }

    public void verifyTenantErrorMessages() {
        WebElement btnSave = driver.findElement(btn_save);
        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
        System.out.println("Click on save, Tenant name is blank");
        String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Error_Name)).getText();
        Assert.assertEquals(errorMessage, "TENANT NAME : Required");
        System.out.println("Mandatory field validation message appeared - 'TENANT NAME : Required'");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).sendKeys("test Name");
        btnSave.click();
        System.out.println("Click on save, Tenant Admin email is blank");
        String errorMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Error_email)).getText();
        Assert.assertEquals(errorMessage2, "EMAIL : Required");
        System.out.println("Mandatory field validation message appeared - 'EMAIL : Required'");
    }

    public void editTenatAdnim(String email, String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_ChangeAdmin));
        scrollIntoView(driver.findElement(btn_ChangeAdmin));
        driver.findElement(btn_ChangeAdmin).click();
        //Edit Tenant admin details
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_newEmail))).sendKeys(email);
        System.out.println("Email : " + email);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_newfirstName))).sendKeys(firstName);
        System.out.println("First Name :" + firstName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_newlastName))).sendKeys(lastName);
        System.out.println("TLast name :" + lastName);
        //click on save button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Add))).click();
        scrollIntoView(driver.findElement(btn_save));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Edit the Tenant");
    }
}
