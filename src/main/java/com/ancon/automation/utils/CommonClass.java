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
   // public static String path= "\\src\\main\\java\\com\\ancon\\automation\\";
    public static String path= "//src//main//java//com//ancon//automation//";
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
    protected String getPageName() {
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
    protected static void scrollIntoView(WebElement element){
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

    protected void buttonExpand(){
        try {
           // WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"));
            WebDriverWait wait2 = new WebDriverWait(driver,3);
            wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"))));
        } catch (NoSuchElementException e) {
            //   e.printStackTrace();
            WebElement btn_expand = driver.findElement(By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']"));
            wait.until(ExpectedConditions.visibilityOf((btn_expand)));
            scrollIntoView(btn_expand);
            wait.until(ExpectedConditions.elementToBeClickable(btn_expand)).click();
            System.out.println("Expand button clicked");
        }
    }

}
