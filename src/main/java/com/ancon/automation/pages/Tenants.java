package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
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
    private WebDriver driver ;
    private WebDriverWait wait;

    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By txt_name = By.xpath("//input[@name='tenantName']");
    private By txt_businessNumber = By.xpath("//input[@name='businessNumber']");
    private By btn_logoUpload = By.xpath("//button[contains(text(),'Upload')]");
    //Create tenant
    private By txt_email = By.xpath("//input[@name='email']");
    private By txt_firstName = By.xpath("//input[@name='firstName']");
    private By txt_lastName = By.xpath("//input[@name='lastName']");
    private By btn_save = By.xpath("//button[contains(text(),'Save')]");
    private By btn_Discard= By.xpath("//button[contains(text(),'Discard')]");
    private By btn_saveNtenant = By.xpath("//button[contains(text(),'Save & View Tenant')]");
    private By input_search = By.xpath("//input[@placeholder='Search Tenants or Outlets']");
    private By txt_OrderNumber = By.xpath("//tbody[1]/tr[1]/td[1][@class='id-td id-content___3cuQ3'][contains(text(),'001')]");

    private By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
    private By btn_Edit_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[2]/button/span[contains(text(),'Edit')]");
    private By btn_Disable_1 = By.xpath("//tbody[1]/tr/td[7]/button/span[contains(text(),'Disable')]");
    private By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
    private By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");
    private By lbl_Error_Name = By.xpath("//form/div[1]/fieldset/div/div[1]/div[1]/label");
    private By lbl_Error_email = By.xpath("//form/div[2]/fieldset/div/div[1]/div[1]/label");

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




    public Tenants(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void verifyPageElements(){
        WebElement searchArea = driver.findElement(input_search);
        Assert.assertTrue(searchArea.isDisplayed());
        System.out.println("Search bar is available");
        WebElement btn_CreateNe = driver.findElement(btn_CreateNew);
        Assert.assertTrue(btn_CreateNe.isDisplayed());
        System.out.println("Create New button is available");
    }

    public void createNewTenant(String hederName){
        sleepTime(2000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateNew))).click();
        Assert.assertEquals(getPageName(),hederName);
        System.out.println("Create a Tenant label is available");
    }

    public void tenantDetails(String name, String businessNumber, String email, String firstName, String lastName){
        System.out.println("-Filling Tenant Details-");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).clear();
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
        System.out.println("Email : "+email);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_firstName))).sendKeys(firstName);
        System.out.println("First Name :"+firstName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_lastName))).sendKeys(lastName);
        System.out.println("TLast name :"+lastName);
        //click on save button
        srollIntoView(driver.findElement(btn_save));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Created a Tenant");
    }

    public void verifyCreatedTenant(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        srollIntoView(driver.findElement(txt_OrderNumber));
        String orderNumber = driver.findElement(txt_OrderNumber).getText();
        Assert.assertEquals(orderNumber,"001");
        System.out.println("Sequence order number '001' is available");
        sleepTime(1000);
        String tname = driver.findElement(By.xpath("//table/tbody[1]/tr[1]/td[3]/div/span")).getText();
        Assert.assertEquals(tname,name);
        System.out.println("Created tenat name verified : "+ name);
        //get date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr/td[4]")).getText();
        Assert.assertEquals(tcdate,dtf.format(now));
        System.out.println("Tenant Created date verified : "+tcdate);
        // verify View button available
        WebElement btn_view = driver.findElement(btn_View_1);
        Assert.assertTrue(btn_view.isDisplayed());
        System.out.println("View button is available");
        //verify edit button available
        WebElement btn_edit = driver.findElement(btn_Edit_1);
        Assert.assertTrue(btn_edit.isDisplayed());
        System.out.println("Edit button is available");
        //verify Disable button available
        WebElement btn_desable = driver.findElement(btn_Disable_1);
        Assert.assertTrue(btn_desable.isDisplayed());
        System.out.println("Disable button is available");
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[4]")).getText();
        Assert.assertEquals(tcdate,dtf.format(now));
        System.out.println("Outlet Created date verified : "+tcdate);
    }

    public void verifyTenantErrorMessages(){
        Actions actions = new Actions(driver);
        WebElement btnSave = driver.findElement(btn_save);
        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
     //   actions.doubleClick(btnSave).build().perform();
      //  actions.doubleClick(btnSave).build().perform();
        System.out.println("Click on save, Tenant name is blank");
        String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Error_Name)).getText();
        Assert.assertEquals(errorMessage,"TENANT NAME : Required");
        System.out.println("Mandatory field validation message appeared - 'TENANT NAME : Required'");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_name))).sendKeys("test Name");
        btnSave.click();
        System.out.println("Click on save, Tenant Admin email is blank");
        String errorMessage2 = wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Error_email)).getText();
        Assert.assertEquals(errorMessage2,"EMAIL : Required");
        System.out.println("Mandatory field validation message appeared - 'EMAIL : Required'");
    }
}
