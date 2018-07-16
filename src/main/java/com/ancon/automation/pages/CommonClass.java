package com.ancon.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by chathura on 11/07/2018.
 */
public class CommonClass {
    static WebDriver driver ;
    static WebDriverWait wait;

    static By lbl_pagename = By.xpath("//h1[contains(text(),'')]");


    public CommonClass(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void selectSidebarMenu(String menuName){
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'"+menuName+"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
        Assert.assertEquals(getPageName(),menuName);
        System.out.println(menuName+ "Header is available");
    }

    public static String getPageName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_pagename));
        return driver.findElement(lbl_pagename).getText();
    }

    public static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
