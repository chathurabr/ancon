package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    private By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");
    private By btn_View_1 = By.xpath("(//SPAN[text()='View'])[1]");
    private By btn_save = By.xpath("//button[contains(text(),'Save')]");
    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By btn_CreateOutlet = By.xpath("//button[contains(text(),'Create Outlet')]");
    // Create Outlet
    private By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
    private By txt_outletName = By.xpath("//input[@name='outletName']");
    private By txt_outletBusinessNumber = By.xpath("//input[@name='outletBusinessNumber']");
    private By txt_street = By.xpath("//input[@name='street']");
    private By txt_zip = By.xpath("//input[@name='zip']");
    private By txt_city = By.xpath("//input[@name='city']");
    private By txt_telephone = By.xpath("//input[@class='react-phone-number-input__input react-phone-number-input__phone']");
    private By btn_BackFromOutlet = By.xpath("//button/i[@class='a_icon-arrow_back']");
    private By dd_StartTime = By.xpath("(//DIV[@class='css-1rtrksz'])[1]");
    private By dd_CloseTime = By.xpath("(//DIV[@class='css-1rtrksz'])[2]");
    private By btn_Edit = By.xpath("(//SPAN[text()='Edit'][text()='Edit'])[2]");
    private By advanced_Routine_Button = By.xpath("//div[contains(@class,'col-md-6')]//button[contains(@type,'button')][contains(text(),'Yes')]");
    private By time_Selection_Table = By.xpath("//table[contains(@class,'table-drag-select')]");
    private By source1 = By.xpath("//tbody//tr[2]//td[10]");
    private By destination1 = By.xpath("//tbody//tr[2]//td[14]");

    //color
    private By cb_PrimaryColor = By.xpath("(//DIV[@class='colorBlockLarge___1aUen'])[1]");
    private By cb_SecondaryColor = By.xpath("(//DIV[@class='colorBlockLarge___1aUen'])[2]");
    private By btn_changeColor = By.xpath("//BUTTON[@type='button'][text()='Change Colors']");
    private By lbl_heder_color = By.xpath("//H5[@class='modal-title'][text()='Change Outlet Colors']");
    private By txt_prmaryColor = By.xpath("(//INPUT[@tabindex='0'])[8]");
    private By txt_secondColor = By.xpath("(//INPUT[@tabindex='0'])[9]");
    private By btn_save_colorbox = By.xpath("//html/body/div[2]/div/div[1]/div/div/div[3]/button[2]");
    // routing time
    private By btn_time_yes = By.xpath("(//BUTTON[@type='button'][text()='Yes'][text()='Yes'])[2]");



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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletName))).sendKeys(outletName);
        System.out.println("Outlet Name :" + outletName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletBusinessNumber))).sendKeys(outletNumber);
        System.out.println("Outlet business number :" + outletNumber);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_street))).sendKeys(street);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_zip))).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_city))).sendKeys(city);
        scrollIntoView(driver.findElement(txt_telephone));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_telephone))).sendKeys(telephone);
        System.out.println("Entered Outlet location - 'Street:' " + street + " 'Zip' : " + zip + " 'City' : " + city + " 'Telephone' : " + telephone + "");
    }

    //Opening Hours
    //Have an advanced routine ? No
    public void nonAdvancedRoutineTime() {
        //set Open time
        WebElement openTime = driver.findElement(dd_StartTime);
        scrollIntoView(openTime);
        openTime.click();
        sleepTime(500);
        try{
        scrollIntoView(driver.findElement(By.xpath("//*[contains(text(),'03:00')]")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(text(),'03:00')]")))).click();
            System.out.println("Entered advanced routine Open Time 03:00");
        }catch(NoSuchElementException e) {
            e.printStackTrace();
            openTime.click();
        }
        try{
        //set Close time
        WebElement closeTime = driver.findElement(dd_CloseTime);
        closeTime.click();
        sleepTime(500);
        WebElement time = driver.findElement(By.xpath("//*[contains(text(),'07:00')]"));
        scrollIntoView(time);
        wait.until(ExpectedConditions.elementToBeClickable(time)).click();
            System.out.println("Entered advanced routine Close Time 07:00");
        }catch(NoSuchElementException e) {
            e.printStackTrace();}

    }

    //Opening Hours
    //Have an advanced routine ? yes
    public void advancedRoutineTimes() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_time_yes));
        scrollIntoView(driver.findElement(btn_time_yes));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_time_yes))).click();
    }

    public void clickOntimebox(int open , int close){
        for (int i = open; i <= close; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//SPAN)["+i+"]")));
            scrollIntoView(driver.findElement(By.xpath("(//SPAN)["+i+"]")));
            driver.findElement(By.xpath("(//SPAN)["+i+"]")).click();

      /*      outlet.clickOntimebox(31,44);  // Tue 30 - 53
            outlet.clickOntimebox(55,74);  // Wed 54 - 77
            outlet.clickOntimebox(78,95);  // Thu 78 - 101
            outlet.clickOntimebox(109,121);  // Fri 102 - 125
            outlet.clickOntimebox(129,148);  // Sat 126 - 149
            outlet.clickOntimebox(156,167);  // Sun 150 - 173*/
        }
    }

    public void colorBox(String pColor, String sColor) {
        WebElement PrimaryColor = driver.findElement(cb_PrimaryColor);
        WebElement SecondaryColor = driver.findElement(cb_SecondaryColor);
        scrollIntoView(PrimaryColor);
        String pcolor = PrimaryColor.getCssValue("background-color");
        Assert.assertTrue(pcolor.contains(pColor));
        System.out.println("Verify default / Selected Primary Color in Outlet Details page");
        String scolor = SecondaryColor.getCssValue("background-color");
        Assert.assertTrue(scolor.contains(sColor));
        System.out.println("Verify default / Selected Secondary Color in Outlet Details page");

    }

    //Get selected Primary Color
    public String getSelectedPrimaryColor(){
        return driver.findElement(By.xpath("(//BUTTON[@tabindex='-1'])[11]")).getCssValue("background-color");
    }
    //Get selected Secondary Color
    public String getSelectedSecondaryColor(){
        return driver.findElement(By.xpath("(//BUTTON[@tabindex='-1'])[13]")).getCssValue("background-color");
    }


    public void changeColor(String hex_P_color, String rgb_P_color, String hex_S_color, String rgb_S_color){
        WebElement btn_Change_Color = driver.findElement(btn_changeColor);
        scrollIntoView(btn_Change_Color);
        wait.until(ExpectedConditions.elementToBeClickable(btn_Change_Color)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_heder_color));
        System.out.println("Change Outlet Colors - popup opened");

        /*Verify default color*/
        Assert.assertTrue(getSelectedPrimaryColor().contains("29, 61, 145"));
        Assert.assertTrue(getSelectedSecondaryColor().contains("249, 89, 25"));
        System.out.println("Verify default Primary and Secondary Color");

        /*Verify Error messages*/
        WebElement primary_Change_Color = driver.findElement(txt_prmaryColor);
        WebElement secondary_Change_Color = driver.findElement(txt_secondColor);
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

        String preview_button_color_back = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/button/i")).getCssValue("color");
      //  String preview_button_color_discard = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div/button[text()='Discard']")).getCssValue("color");
        String preview_button_color_discard = driver.findElement(By.cssSelector(".mainButtons___2Q9RS > div:nth-child(2) > button:nth-child(1)")).getCssValue("color");
       // String preview_button_color_save = driver.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div/button[text()='Save']")).getCssValue("background-color");
        String preview_button_color_save = driver.findElement(By.cssSelector(".mainButtons___2Q9RS > div:nth-child(2) > button:nth-child(2)")).getCssValue("background-color");

        try {
            Assert.assertTrue(preview_button_color_back.contains(rgb_P_color));
            Assert.assertEquals(preview_button_color_discard,preview_button_color_save);
            System.out.println("Primary Color (Used for buttons and links) - Verified selected colors");
            //save color box
            driver.findElement(btn_save_colorbox).click();
            System.out.println("Successfully changed the colors");

        } catch (AssertionError e) {
            e.printStackTrace();
            driver.findElement(btn_save_colorbox).click();
            System.out.println("Error in Primary Color (Used for buttons and links)");
/*            System.out.println("--preview_button_color_back:" + preview_button_color_back);
            System.out.println("--preview_button_color_discard:" + preview_button_color_discard);
            System.out.println("--preview_button_color_save:" + preview_button_color_save);
            System.out.println("--rgb_P_color"+rgb_P_color);*/
        }
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
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletName))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletName))).sendKeys("edited"+outletName);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletBusinessNumber))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_outletBusinessNumber))).sendKeys(outletNumber);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_street))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_street))).sendKeys(street);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_zip))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_zip))).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_city))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_city))).sendKeys(city);
        scrollIntoView(driver.findElement(txt_telephone));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_telephone))).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_telephone))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(txt_telephone))).sendKeys(telephone);
        System.out.println("Successfully edit the Outlet details");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
    }

    public void selectAdvancedRoutine() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(advanced_Routine_Button))).click();
    }

    public void selectSingleOpeningTime(String outletName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//td[@class='btn-td']//a[2]//button[1]")))).click();
        Assert.assertEquals(getPageName(), "Edit an Outlet");
        scrollIntoView(driver.findElement(advanced_Routine_Button));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(advanced_Routine_Button))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(time_Selection_Table));
        WebElement element1 = driver.findElement(source1);
        WebElement target1 = driver.findElement(destination1);
        (new Actions(driver)).dragAndDrop(element1, target1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_save))).click();
    /*    wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody[1]/tr[2]/td[7]")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//td[@class='btn-td']//a[1]//button[1]")))).click();
        System.out.println("View Outlet button clicked");
        Assert.assertEquals(getPageName(), "Outlet Details");
        WebElement timeView = driver.findElement(By.xpath("//div[contains(@class,'tag___1sqlq')]/span"));
        scrollIntoView(timeView);
        String openingTime = timeView.getText();
        Assert.assertEquals(openingTime,"Everyday Opens at 12:00 - 16:00 hours");*/

    }

}
