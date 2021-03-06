package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.*;
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
    //Delete Popup
    private By btn_No = By.xpath("//button[contains(text(),'No')]");
    private By btn_Yes = By.xpath("//button[contains(text(),'Yes')]");

    private By btn_view_outlet = By.xpath("//td[@class='btn-td']//a[1]");
    private By btn_edit_outlet = By.xpath("//td[@class='btn-td']//a[2]");

    WebElement searchField;
    WebElement searchButton;

    private String tenatName;
    private String outletName;

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
        WebElement tenantNameElement = driver.findElement(By.xpath("//html/body/div/div/div/div/div[2]/div/div/div/div/div[2]/div[1]/div[1]/table/tbody[1]/tr[1]/td[3]/div"));
        return tenantNameElement.getText();
    }

    public String getOutletname() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
       // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        //buttonExpand();
        WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"));
        return outletNameElement.getText();
    }

    public String getHeadername() {
        WebElement headerNameElement = driver.findElement(By.xpath("//div[@class='modal-content']/div[1]/h5"));
        return headerNameElement.getText();
    }

    //Click View Outlet in Summary page
    public void clickViewOutlet() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_view_outlet)).click();
        System.out.println("Navigated to View outlet page");
    }

    //Click Edit Outlet in Summary page
    public void clickEditOutlet() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_edit_outlet)).click();
        System.out.println("Navigated to Edit outlet page");
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
        WebElement btn_enable_1 = driver.findElement(btn_Enable_1);
        Assert.assertTrue(btn_enable_1.isDisplayed());
        System.out.println("Enable button is available");
        //verify Tenant enable button available
        WebElement btn_delete = driver.findElement(btn_Delete_1);
        Assert.assertTrue(btn_delete.isDisplayed());
        System.out.println("Delete button is available");
    }

    public void disableOutletCancel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        try {
            WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"));

        } catch (NoSuchElementException e) {
            // e.printStackTrace();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_2))).click();
        if (getHeadername().toLowerCase().contains(getOutletname().toLowerCase())) {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Cancel_1))).click();
            System.out.println("Cancel Disable popup Successfully");
        }
    }

    public void disableOutletanyway() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        try {
            WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span"));

        } catch (NoSuchElementException e) {
           // e.printStackTrace();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        }
           wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disable_2))).click();
        if (getHeadername().toLowerCase().contains(getOutletname().toLowerCase())) {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Disableanyway_1))).click();
            System.out.println("Disable Outlet Successfully");
        }
    }

    public void verifyButtonChangeOutlet() {
        WebElement btn_enable_2 = driver.findElement(btn_Enable_2);
        Assert.assertTrue(btn_enable_2.isDisplayed());
        System.out.println("Enable button is available");
        //verify Outlet enable button available
        WebElement btn_delete_2 = driver.findElement(btn_Delete_2);
        Assert.assertTrue(btn_delete_2.isDisplayed());
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
    public void verifyTenantDetails(String name,String numberOutlets) {
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
        //number of outlets
        WebElement outlets = driver.findElement(lbl_Outlets);
        String numberOfOutlets = wait.until(ExpectedConditions.elementToBeClickable(outlets)).getText();
        Assert.assertEquals(numberOfOutlets,numberOutlets);
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
    public void deleteOutletCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Delete_2))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_No))).click();
        System.out.println("Canceled delete Outlet popup Successfully");
    }

    public void deleteOutlet() {
        outletName = getOutletname();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Delete_2))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Yes))).click();
        System.out.println("Delete Outlet Successfully");
    }
    public void deleteTenantCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Delete_1))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_No))).click();
        System.out.println("Cancelled Delete Tenant popup Successfully");
    }
    public void deleteTenant() {
        tenatName = getTenantName();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Delete_1))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_Yes))).click();
        System.out.println("Delete Tenant Successfully");
    }
    public void verifyOutletDelete(){
        try {
            WebElement outletNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span[text()='" + outletName + "']"));
            System.out.println("Delete Outlet Unsuccessfully");
        } catch (NoSuchElementException e) {
            // e.printStackTrace();
            System.out.println("Outlet Deleted Successfully");
        }
    }
    public void verifyTenantDelete(){
        try {
            WebElement tenantNameElement = driver.findElement(By.xpath("//table/tbody[1]/tr[1]/td[3]/div/span[text()='" + tenatName + "']"));
            System.out.println("Delete Tenant Unsuccessfully");
        } catch (NoSuchElementException e) {
            // e.printStackTrace();
            System.out.println("Tenant Deleted Successfully");

        }
    }
}
