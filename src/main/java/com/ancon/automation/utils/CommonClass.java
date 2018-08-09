package com.ancon.automation.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * Created by chathura on 11/07/2018.
 */
public class CommonClass {
    public static String path= "\\src\\main\\java\\com\\ancon\\automation\\";
    private static WebDriver driver ;
    private  WebDriverWait wait;

    private static By lbl_pagename = By.xpath("//h1[contains(text(),'')]");


    public CommonClass(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    // select menu from side bar
    public void selectSidebarMenu(String menuName){
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'"+menuName+"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
        System.out.println("Select menu - "+ menuName);
        Assert.assertEquals(getPageName(),menuName);
        System.out.println(menuName+ " Header is available");
    }

    // get page name from header
    public String getPageName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_pagename));
        return driver.findElement(lbl_pagename).getText();
    }

    // Thread sleep
    public static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Scroll the page
    public static void scrollIntoView(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

}
