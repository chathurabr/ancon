package com.ancon.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by chathura on 03/07/2018.
 */
public class Login  {

    WebDriver driver ;
    WebDriverWait wait;

    By txt_useremail = By.xpath("//input[@name='email']");
    By txt_password = By.xpath("//input[@name='password']");
    By btn_Submit = By.xpath("//button[contains(text(),'Submit')]");
    By lbl_dashboard = By.xpath("//h1[contains(text(),'Dashboard')]");


    public Login(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void loginToAncon(String email, String password) {
        WebElement loginEmail = driver.findElement(txt_useremail);
        loginEmail.clear();
        loginEmail.sendKeys(email);
        System.out.println("Enter Username");
        WebElement loginpassword = driver.findElement(txt_password);
        loginpassword.clear();
        loginpassword.sendKeys(password);
        System.out.println("Enter password");
        driver.findElement(btn_Submit).click();
        Assert.assertEquals(getUsername(), "Dashboard");
        System.out.println("Successfully loged in to the Ancon");
    }

    public String getUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_dashboard));
        return driver.findElement(lbl_dashboard).getText();
    }


}