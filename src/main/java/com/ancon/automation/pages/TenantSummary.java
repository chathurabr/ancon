package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TenantSummary extends CommonClass {
    private WebDriver driver;
    private WebDriverWait wait;
    //search Tenant
    private By search_field = By.xpath("//div[@class='container___2Ufnl']/input");
    private By search_Field_Placeholder = By.xpath("//input[@placeholder='Search Tenants or Outlets']");
    private By search_Results_Page_Title = By.xpath("//h1[contains(text(),'Search Tenants or Outlets')]");
    private By search_No_Results_Found = By.xpath("//h2[contains(text(),'No matching results found')]");
    private By getSearch_result_Table_Rows = By.xpath("//table/tbody");
    private By getSearch_Outlet_result_Table_Rows = By.xpath("//div[@class='table-responsive']//tbody//tr[2]");
    private By getSearch_result_Table_Rows2 = By.xpath("//table[@class='ancon-table table']//tbody[1]//tr[1]//td[7]");
    private By search_Button = By.xpath("//div[@class='search-button']");

    private By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");
    private By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By txt_OrderNumber = By.xpath("//td[@class='id-td id-content___3cuQ3'][text()='001']");


    //Tenant_home_button
    private By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
    private By btn_View_2 = By.xpath("//table/tbody[1]/tr[@class'inner']/td[7]/a[1]/button/span[contains(text(),'View')]");
    private By btn_Edit_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[2]/button/span[contains(text(),'Edit')]");
    private By btn_Edit_2 = By.xpath("//table/tbody[1]/tr[@class'inner']/td[7]/a[2]/button/span[contains(text(),'Edit')]");
    private By btn_Disable_1 = By.xpath("//tbody[1]/tr/td[7]/button/span[contains(text(),'Disable')]");
    private By btn_Disable_2 = By.xpath("//tbody//tr[2]//td[7]/button");
    private By btn_Enable_1 = By.xpath("//tbody[@class='disabled']/tr/td[7]/button/span[contains(text(),'Enable')]");
    private By btn_Enable_2 = By.xpath("//tr[@class='inner']/td[7]/button/span[contains(text(),'Enable')]");
    private By btn_Delete_1 = By.xpath("//tbody[1]/tr/td[7]/button[2]/span[contains(text(),'Delete')]");
    private By btn_Delete_2 = By.xpath("//tr[@class='inner']/td[7]/button[2]/span[contains(text(),'Delete')]");
    private By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
    //Disable popup
    private By btn_Cancel_1 = By.xpath("//button[contains(text(),'Cancel')]");
    private By btn_Disableanyway_1 = By.xpath("//div[@class='modal-content']/div[3]/button[contains(text(),'Disable Anyway')]");


    WebElement searchField;
    WebElement searchButton;

    public TenantSummary(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 30);
        this.driver = driver;
    }

    //Search Tenants

    public void searchFunction(String searchTerm) {
        searchField = driver.findElement(search_field);
        searchButton = driver.findElement(search_Button);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(search_field))).click();
        searchField.sendKeys(searchTerm);
        searchButton.click();
    }

    public void searchFunctionWithEnterKey(String searchTerm) {
        searchField = driver.findElement(search_field);
        searchButton = driver.findElement(search_Button);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(search_field))).click();
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
    }

    public void verifyPlaceholderText() {
        String searchFieldText = driver.findElement(search_field).getAttribute("placeholder");
        Assert.assertEquals(searchFieldText, "Search Tenants or Outlets");
        System.out.println("Correct Placeholder text is displayed");
    }

    public void searchNonExistingTenant(String tenantValue) {
        searchFunction(tenantValue);
        Assert.assertTrue(driver.findElement(search_No_Results_Found).isDisplayed());
        System.out.println("No Result Found message is displayed for Non Existing Tenants");
    }

    public void searchTenant(String tenantValue) {
        searchFunction(tenantValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(search_Results_Page_Title));
        int Row_count = driver.findElements(getSearch_result_Table_Rows).size();
        for (int i = 1; i <= Row_count; i++) {
            String rowXpath = "//table[@class='ancon-table table']//tbody[" + i + "]//tr[1]//td[3]/div/span";
            String rowData = driver.findElement(By.xpath(rowXpath)).getText();
            System.out.println("Row Data:  " + rowData.toLowerCase());
            System.out.println("Tenant Value:  " + tenantValue);
            Assert.assertTrue(rowData.toLowerCase().contains(tenantValue.toLowerCase()));
        }
        System.out.println("Search Results correctly displayed");
    }

    public void searchOutlet(String outletValue) {
        searchFunction(outletValue);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(search_Results_Page_Title)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(getSearch_result_Table_Rows)));
        int Row_count = driver.findElements(getSearch_Outlet_result_Table_Rows).size();
        for (int j = 1; j <= Row_count; j++) {
            String outletRowXpath = "//div[@class='table-responsive']//tbody[" + j + "]//tr[2]//td[3]//div[1]/span";
            String outletRowData = driver.findElement(By.xpath(outletRowXpath)).getText();
            Assert.assertTrue(outletRowData.toLowerCase().contains(outletValue));
        }

        System.out.println("Search Results correctly displayed");
    }

    public String getTenantName() {
        WebElement TenantLastnameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[1]/td[3]/div/span"));
        return TenantLastnameElement.getText();
    }

    public String getOutletname() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"));
        return outletNameElement.getText();
    }

    public String getHeadername() {
        WebElement HeadernameElement = driver.findElement(By.xpath("//div[@class='modal-content']/div[1]/h5"));
        return HeadernameElement.getText();
    }

    public void disableTenantCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_1))).click();
        if (getHeadername().toLowerCase().contains(getTenantName().toLowerCase())) {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Cancel_1))).click();
            System.out.println("Cancel popup Successfully");
        } else {

            System.out.println("Popup is not valid");
        }

    }

    public void disableTenantAnyway() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_1))).click();
        if (getHeadername().toLowerCase().contains(getTenantName().toLowerCase())) {

            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disableanyway_1))).click();
            System.out.println("Disable Tenant Successfully");
        } else {

            System.out.println("Popup is not valid");
        }
    }

    public void verifyButtonChangeTenant() {
        //verify Tenant Disable button available
        WebElement btn_enable = driver.findElement(btn_Enable_1);
        Assert.assertTrue(btn_enable.isDisplayed());
        System.out.println("Enable button is available");
        //verify Tenant enable button available
        WebElement btn_delete = driver.findElement(btn_Delete_1);
        Assert.assertTrue(btn_delete.isDisplayed());
        System.out.println("Delete button is available");
    }

    public void disableOutletCancel() {
        scrollIntoView(driver.findElement(By.xpath("//html/body/div/div/div/div/div[2]/div/div/div/div/div[1]/h1")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_2))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Cancel_1))).click();
        System.out.println("Cancel popup Successfully");
    }

    public void disableOutletanyway() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_2))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disableanyway_1))).click();
        System.out.println("Disable Outlet Successfully");
    }

    public void verifyButtonChangeOutlet() {
        WebElement btn_enable_1 = driver.findElement(btn_Enable_2);
        Assert.assertTrue(btn_enable_1.isDisplayed());
        System.out.println("Enable button is available");
        //verify Outlet enable button available
        WebElement btn_delete_1 = driver.findElement(btn_Delete_2);
        Assert.assertTrue(btn_delete_1.isDisplayed());
        System.out.println("Delete button is available");
    }

    // Verify outlet details in summary page
    public void verifyOutlet(String OutletName1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//SPAN[text()='" + OutletName1 + "']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody[1]/tr[2]/td[7]")));
        String Outletname = driver.findElement(txt_outletNeme).getText();
        Assert.assertEquals(Outletname, OutletName1);
        System.out.println("Created Outlet name verified : " + OutletName1);
        WebElement outlets = driver.findElement(lbl_Outlets);
        String numberOfOutlets = wait.until(ExpectedConditions.elementToBeClickable(outlets)).getText();
        Assert.assertEquals(numberOfOutlets, "1");
        System.out.println("Verified Number Of Outlets : " + numberOfOutlets);
        //get date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[4]")).getText();
        Assert.assertEquals(tcdate, dtf.format(now));
        System.out.println("Outlet Created date verified : " + tcdate);
    }

    // Verify Tenant details in summary page
    public void verifyTenantDetails(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_CreateNew));
        scrollIntoView(driver.findElement(By.xpath("//DIV[@role='button'][text()='Name']")));
        sleepTime(2000);
        String orderNumber = driver.findElement(txt_OrderNumber).getText();
        Assert.assertEquals(orderNumber, "001");
        System.out.println("Sequence order number '001' is available");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody[1]/tr/td[3]/div/span")));
        String tname = driver.findElement(By.xpath("//table/tbody[1]/tr/td[3]/div/span")).getText();
        Assert.assertEquals(tname, name);
        System.out.println("Tenant name verified : " + name);
        //get date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String tcdate = driver.findElement(By.xpath("//table/tbody[1]/tr/td[4]")).getText();
        Assert.assertEquals(tcdate, dtf.format(now));
        System.out.println("Tenant Created date verified : " + tcdate);
        // verify View button available
        WebElement btn_view = driver.findElement(btn_View_1);
        Assert.assertTrue(btn_view.isDisplayed());
        //verify edit button available
        WebElement btn_edit = driver.findElement(btn_Edit_1);
        Assert.assertTrue(btn_edit.isDisplayed());
        //verify Disable button available
        WebElement btn_desable = driver.findElement(btn_Disable_1);
        Assert.assertTrue(btn_desable.isDisplayed());
        System.out.println("View,Edit and Disable buttons are available");
    }
}
