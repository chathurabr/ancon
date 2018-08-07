package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Created by chathura on 07/08/2018.
 */
public class Outlet extends CommonClass{

        private WebDriver driver ;
        private WebDriverWait wait;

        private By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
        private By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");
        private By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
        private By btn_save = By.xpath("//button[contains(text(),'Save')]");
        private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
        private By btn_CreateOutlet = By.xpath("//button[contains(text(),'Create Outlet')]");
        // Create Outlet
        private By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
        private By txt_outletName = By.xpath("//input[@name='outletName']");
        private By txt_otletBusinessNumber = By.xpath("//input[@name='outletBusinessNumber']");
        private By txt_street = By.xpath("//input[@name='street']");
        private By txt_zip = By.xpath("//input[@name='zip']");
        private By txt_city = By.xpath("//input[@name='city']");
        private By txt_telephone = By.xpath("//input[@class='react-phone-number-input__input react-phone-number-input__phone']");
        private By btn_BackFromOutlet = By.xpath("//button/i[@class='a_icon-arrow_back']");
        private By dd_StartTime = By.cssSelector("//svg[@class='css-19bqh2r']");
        private By dd_CloseTime = By.xpath("//div[@class='Select-placeholder'][contains(text(),'20:00')]");
        //color
        private By cb_PrimaryColor = By.cssSelector("div.colorBlockLarge___1aUen");
        private By cb_SecondaryColor = By.xpath("//*[contains(text(),'Secondary Color')]/following-sibling::div");




        public Outlet(WebDriver driver) {
            super(driver);
            this.wait = new WebDriverWait(driver, 30);
            this.driver = driver;
        }


        public void createOutlet(String outletName,String outletNumber,String street,String zip, String city, String telephone) {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_View_1))).click();
            System.out.println("Clicked on View button");
            Assert.assertEquals(getPageName(), "Tenant Details");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateOutlet))).click();
            System.out.println("Create Outlet button clicked");
            Assert.assertEquals(getPageName(), "Create an Outlet");
            //Outlet details
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletName))).sendKeys(outletName);
            System.out.println("Outlet Name :" + outletName);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_otletBusinessNumber))).sendKeys(outletNumber);
            System.out.println("Outlet business number :" + outletNumber);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_street))).sendKeys(street);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_zip))).sendKeys(zip);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_city))).sendKeys(city);
            srollIntoView(driver.findElement(txt_telephone));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_telephone))).sendKeys(telephone);
            System.out.println("Entered Outlet location - 'Street:' " + street + " 'Zip' : " + zip + " 'City' : " + city + " 'Telephone' : " + telephone + "");
        }

        //Opening Hours
        //Have an advanced routine ? Yes
        public void createOutletRoutineTme() {
            //set Open time
            WebElement openTime = driver.findElement(dd_StartTime);
            srollIntoView(openTime);
            openTime.click();
            sleepTime(1000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(text(),'03:00')]")))).click();
            //set Close time
            WebElement closeTime = driver.findElement(dd_CloseTime);
            closeTime.click();
            sleepTime(1000);
            WebElement time = driver.findElement(By.xpath("//*[contains(text(),'07:00')]"));
            srollIntoView(time);
            wait.until(ExpectedConditions.elementToBeClickable(time)).click();
            System.out.println("Entered advanced routine Open and Close Times (03:00 - 07:00)");
        }

        public void colorBox(){
            WebElement PrimaryColor = driver.findElement(cb_PrimaryColor);
            WebElement SecondaryColor = driver.findElement(cb_SecondaryColor);
            String pcolor = PrimaryColor.getCssValue("background-color");
            Assert.assertEquals(pcolor,"rgb(29, 61, 145)");
            System.out.println("Verify Primary Color - rgb(29, 61, 145)");
            String scolor = SecondaryColor.getCssValue("background-color");
            Assert.assertEquals(scolor,"rgb(249, 89, 25)");
            System.out.println("Verify Secondary Color - rgb(249, 89, 25)");
        }

        //save Created Outlet
        public void saveCreateOutlet(){
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
            System.out.println("successfully Created an Outlet");
            //Back to Tenants Summary page
            sleepTime(3000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(btn_BackFromOutlet));
            WebElement backButton = driver.findElement(btn_BackFromOutlet);
            srollIntoView(backButton);
            wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
            Assert.assertEquals(getPageName(),"Tenants");
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
            //get date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime now = LocalDateTime.now();
            String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[4]")).getText();
            Assert.assertEquals(tcdate,dtf.format(now));
            System.out.println("Outlet Created date verified : "+tcdate);
        }


}