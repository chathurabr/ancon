package com.ancon.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    public Tenants(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void createNewTenant(String hederName){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateNew))).click();
        org.testng.Assert.assertEquals(CommonClass.getPageName(),hederName);
    }

    public void tenantDetails(String name, String businessNumber, String email, String firstName, String lastName){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_businessNumber))).sendKeys(businessNumber);
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_firstName))).sendKeys(firstName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_lastName))).sendKeys(lastName);
        //click on save button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();

    }
}
