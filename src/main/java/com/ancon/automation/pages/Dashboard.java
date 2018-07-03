package com.ancon.automation.pages;

import com.ancon.automation.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Dashboard extends DriverFactory {
    WebDriver driver;

    By headerDashboard = By.xpath("//*[@id='root']/div/div/div/div[2]/div/div/div/h1");

    public Dashboard(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void DashboardTest() {
        WebElement lblDashboard = driver.findElement(headerDashboard);
        Assert.assertEquals(lblDashboard.getText(),"Dashboard");
    }



}
