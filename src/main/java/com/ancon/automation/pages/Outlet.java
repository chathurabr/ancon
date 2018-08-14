package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;

/**
 * Created by chathura on 07/08/2018.
 */
public class Outlet extends CommonClass {

    private WebDriver driver;
    private WebDriverWait wait;

    private By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
    private By btn_View_1 = By.xpath("(//SPAN[text()='View'])[1]");
    private By btn_save = By.xpath("//button[contains(text(),'Save')]");
    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By btn_CreateOutlet = By.xpath("//button[contains(text(),'Create Outlet')]");
    // Create Outlet
    private By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
    private By txt_OutletName = By.xpath("//input[@name='outletName']");
    private By txt_OutletBusinessNumber = By.xpath("//input[@name='outletBusinessNumber']");
    private By txt_Street = By.xpath("//input[@name='street']");
    private By txt_Zip = By.xpath("//input[@name='zip']");
    private By txt_City = By.xpath("//input[@name='city']");
    private By txt_Telephone = By.xpath("//input[@class='react-phone-number-input__input react-phone-number-input__phone']");
    private By btn_BackFromOutlet = By.xpath("//button/i[@class='a_icon-arrow_back']");
    private By dd_StartTime = By.xpath("(//DIV[@class='css-1rtrksz'])[1]");
    private By dd_CloseTime = By.xpath("(//DIV[@class='css-1rtrksz'])[2]");
    private By btn_Edit = By.xpath("(//SPAN[text()='Edit'][text()='Edit'])[2]");
    //color
    private By cb_Primary_Color = By.xpath("(//DIV[@class='colorBlockLarge___1aUen'])[1]");
    private By cb_Secondary_Color = By.xpath("(//DIV[@class='colorBlockLarge___1aUen'])[2]");
    private By btn_Change_Color = By.xpath("//BUTTON[@type='button'][text()='Change Colors']");
    private By lbl_Header_color = By.xpath("//H5[@class='modal-title'][text()='Change Outlet Colors']");
    private By txt_Primary_Color = By.xpath("(//INPUT[@tabindex='0'])[8]");
    private By txt_Second_Color = By.xpath("(//INPUT[@tabindex='0'])[9]");


    public Outlet(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    public void createOutlet(String outletName, String outletNumber, String street, String zip, String city, String telephone) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_View_1))).click();
        System.out.println("Clicked on View button");
        Assert.assertEquals(getPageName(), "Tenant Details");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_CreateOutlet))).click();
        System.out.println("Create Outlet button clicked");
        Assert.assertEquals(getPageName(), "Create an Outlet");
        //Outlet details
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletName))).sendKeys(outletName);
        System.out.println("Outlet Name :" + outletName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletBusinessNumber))).sendKeys(outletNumber);
        System.out.println("Outlet business number :" + outletNumber);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Street))).sendKeys(street);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Zip))).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_City))).sendKeys(city);
        scrollIntoView(driver.findElement(txt_Telephone));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Telephone))).sendKeys(telephone);
        System.out.println("Entered Outlet location - 'Street:' " + street + " 'Zip' : " + zip + " 'City' : " + city + " 'Telephone' : " + telephone + "");
    }

    //Opening Hours
    //Have an advanced routine ? Yes
    public void createOutletRoutineTime() {
        //set Open time
        WebElement openTime = driver.findElement(dd_StartTime);
        scrollIntoView(openTime);
        openTime.click();
        sleepTime(500);
        WebElement otime = driver.findElement(By.xpath("//*[contains(text(),'07:00')]"));
        scrollIntoView(otime);
        wait.until(ExpectedConditions.elementToBeClickable(otime)).click();
        //set Close time
        WebElement closeTime = driver.findElement(dd_CloseTime);
        closeTime.click();
        sleepTime(500);
        WebElement time = driver.findElement(By.xpath("//*[contains(text(),'07:00')]"));
        scrollIntoView(time);
        wait.until(ExpectedConditions.elementToBeClickable(time)).click();
        System.out.println("Entered advanced routine Open and Close Times (03:00 - 07:00)");
    }

    public void colorBox() {
        WebElement PrimaryColor = driver.findElement(cb_Primary_Color);
        WebElement SecondaryColor = driver.findElement(cb_Secondary_Color);
        scrollIntoView(PrimaryColor);
        String pcolor = PrimaryColor.getCssValue("background-color");
       // Assert.assertEquals(pcolor, "rgba(29, 61, 145, 1)");
        Assert.assertTrue(pcolor.contains("(29, 61, 145"));
        System.out.println("Verify default Primary Color");
        String scolor = SecondaryColor.getCssValue("background-color");
       // Assert.assertEquals(scolor, "rgba(249, 89, 25, 1)");
        Assert.assertTrue(scolor.contains("(249, 89, 25"));
        System.out.println("Verify default Secondary Color");

    }

    //Get selected Primary Color
    public String getSelectedPrimaryColor(){
        return driver.findElement(By.xpath("(//BUTTON[@tabindex='-1'])[11]")).getCssValue("background-color");
    }
    //Get selected Secondary Color
    public String getSelectedSecondaryColor(){
        return driver.findElement(By.xpath("(//BUTTON[@tabindex='-1'])[13]")).getCssValue("background-color");
    }

    public Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    public void changeColor(String hex_P_color, String rgb_P_color, String hex_S_color, String rgb_S_color){
        WebElement btn_Change_Color = driver.findElement(this.btn_Change_Color);
        scrollIntoView(btn_Change_Color);
        wait.until(ExpectedConditions.elementToBeClickable(btn_Change_Color)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_Header_color));
        System.out.println("Change Outlet Colors - popup opened");

        /*Verify default color*/
        Assert.assertTrue(getSelectedPrimaryColor().contains("29, 61, 145"));
        Assert.assertTrue(getSelectedSecondaryColor().contains("249, 89, 25"));
        System.out.println("Verify default Primary and Secondary Color");

        /*Verify Error messages*/
        WebElement primary_Change_Color = driver.findElement(txt_Primary_Color);
        WebElement secondary_Change_Color = driver.findElement(txt_Second_Color);
        wait.until(ExpectedConditions.elementToBeClickable(primary_Change_Color)).sendKeys("test");
        wait.until(ExpectedConditions.elementToBeClickable(secondary_Change_Color)).sendKeys("test");
        String getErrorMessageP = driver.findElement(By.xpath("(//LABEL[@class='control-label error-label'][text()='INVALID COLOR'])[1]")).getText();
        String getErrorMessageS = driver.findElement(By.xpath("(//LABEL[@class='control-label error-label'][text()='INVALID COLOR'])[2]")).getText();
        Assert.assertTrue(getSelectedPrimaryColor().contains("247, 247, 250"));
        Assert.assertTrue(getSelectedSecondaryColor().contains("247, 247, 250"));
        Assert.assertEquals(getErrorMessageP,"INVALID COLOR");
        Assert.assertEquals(getErrorMessageS,"INVALID COLOR");
        System.out.println("'INVALID COLOR' Error message is appearing for PRIMARY and SECONDARY COLOR");

        /*Verify Selected Color*/
        //send hex values to the text field
        wait.until(ExpectedConditions.elementToBeClickable(primary_Change_Color)).clear();
        primary_Change_Color.sendKeys(hex_P_color);
        wait.until(ExpectedConditions.elementToBeClickable(secondary_Change_Color)).clear();
        secondary_Change_Color.sendKeys(hex_S_color);
        // verify rgb values
        Assert.assertTrue(getSelectedPrimaryColor().contains(rgb_P_color));
        Assert.assertTrue(getSelectedSecondaryColor().contains(rgb_S_color));
        //verify preview
        //Used as main background color
        String preview_S_color = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[1]")).getCssValue("background-color");
        Assert.assertTrue(preview_S_color.contains(rgb_S_color));
        System.out.println("SECONDARY COLOR (Used as main background color) - Verified");

        //Used for buttons and links
        String preview_button_color_back = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/button")).getCssValue("color");
        String preview_button_color_discard = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div/button[text()='Discard']")).getCssValue("color");
        String preview_button_color_save = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div/button[text()='Save']")).getCssValue("background-color");

        System.out.println("preview_button_color_back:" + preview_button_color_back);
        System.out.println("preview_button_color_discard:" + preview_button_color_discard);
        System.out.println("preview_button_color_save:" + preview_button_color_save);
        System.out.println("rgb_P_color"+rgb_P_color);

        Assert.assertTrue(preview_button_color_discard.contains(rgb_P_color));
        Assert.assertTrue(preview_button_color_back.contains(rgb_P_color));
        Assert.assertTrue(preview_button_color_save.contains(rgb_P_color));
        System.out.println("Primary Color (Used for buttons and links) - Verified selected colors");
        //save color box
        driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[3]/button[2]")).click();
        System.out.println("Successfully changed the colors");
    }

    //save Created Outlet
    public void saveCreateOutlet() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
        System.out.println("successfully Created an Outlet");
        //Back to Tenants Summary page
        sleepTime(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_BackFromOutlet));
        WebElement backButton = driver.findElement(btn_BackFromOutlet);
        scrollIntoView(backButton);
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        Assert.assertEquals(getPageName(), "Tenants");
        System.out.println("Back to Tenants Summary page successfully ");
    }

    public void editCreatedOutlet(String outletName, String outletNumber, String street, String zip, String city, String telephone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//SPAN[text()='"+outletName+"']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody[1]/tr[2]/td[7]")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Edit))).click();
        System.out.println("Edit Outlet button clicked");
        Assert.assertEquals(getPageName(), "Edit an Outlet");
        //Outlet details
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletName))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletName))).sendKeys("edited"+outletName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletBusinessNumber))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_OutletBusinessNumber))).sendKeys(outletNumber);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Street))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Street))).sendKeys(street);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Zip))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Zip))).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_City))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_City))).sendKeys(city);
        scrollIntoView(driver.findElement(txt_Telephone));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Telephone))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Telephone))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_Telephone))).sendKeys(telephone);
        System.out.println("Successfully edit the Outlet details");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
    }


}
