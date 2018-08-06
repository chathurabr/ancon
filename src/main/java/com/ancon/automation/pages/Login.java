package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
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

    private WebDriver driver ;
    private WebDriverWait wait;

    private By txt_username = By.xpath("//input[@id='Username']");
    private By txt_password = By.xpath("//input[@id='password']");
    //private By btn_Submit = By.xpath("//input[@name='button']");
    private By btn_Submit = By.xpath("//button[contains(text(),'Gå vidare med email')]");
    private By lbl_dashboard = By.xpath("//h1[contains(text(),'Dashboard')]");


    public Login(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void loginToAncon(String email, String password) {
        CommonClass.sleepTime(2000);
        WebElement loginEmail = driver.findElement(txt_username);
        wait.until(ExpectedConditions.visibilityOf(loginEmail));
        wait.until(ExpectedConditions.elementToBeClickable(txt_username));
        loginEmail.clear();
        loginEmail.sendKeys(email);
        WebElement loginpassword = driver.findElement(txt_password);
        loginpassword.clear();
        loginpassword.sendKeys(password);
        driver.findElement(btn_Submit).click();
        Assert.assertEquals(getUsername(), "Dashboard");
        System.out.println("Successfully logged in to the Ancon");
    }

    private String getUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_dashboard));
        return driver.findElement(lbl_dashboard).getText();
    }


}