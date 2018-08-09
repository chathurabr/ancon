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

public class TenantSummary extends CommonClass {
    private WebDriver driver;
    private WebDriverWait wait;
    //search Tenant
    private By search_field = By.xpath("//div[@class='container___2Ufnl']/input");
    private By search_Results_Page_Title = By.xpath("//h1[contains(text(),'Search Tenants or Outlets')]");
    private By search_result_Table = By.xpath(("//table[@class='ancon-table table']"));
    private By search_No_Results_Found = By.xpath("//h2[contains(text(),'No matching results found')]");
    private By getSearch_result_Table_Rows = By.xpath("//table[@class='ancon-table table']//tbody");
    private By search_Button = By.xpath("//div[@class='search-button']");
    private By btn_expand = By.xpath("//table/tbody[1]/tr[1]/td[2]/button/i[@class='a_icon-unfold']");
    private By txt_outletNeme = By.xpath("//table/tbody[1]/tr[2]/td[3]/div/span");
    private By lbl_Outlets = By.xpath("//tbody[1]/tr[1]/td[5]");
    private By btn_CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    private By txt_OrderNumber = By.xpath("//td[@class='id-td id-content___3cuQ3'][text()='001']");
    private By btn_View_1 = By.xpath("//table/tbody[1]/tr/td[7]/a[1]/button/span[contains(text(),'View')]");
    private By btn_Edit_1 = By.xpath("(//SPAN[text()='Edit'])[1]");
    private By btn_Disable_1 = By.xpath("//tbody[1]/tr/td[7]/button/span[contains(text(),'Disable')]");

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

    public void searchNonExistingTenant(String tenantValue) {
        searchFunction(tenantValue);
        Assert.assertTrue(driver.findElement(search_No_Results_Found).isDisplayed());
        System.out.println("No Result Found message is displayed for Non Existing Tenants");
    }

    public void searchTenant(String tenantValue) {
        searchFunction(tenantValue);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(search_Results_Page_Title)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(getSearch_result_Table_Rows)));
        int Row_count = driver.findElements(getSearch_result_Table_Rows).size();
        for (int i = 1; i <= Row_count; i++) {
            String rowXpath = "//table[@class='ancon-table table']//tbody[" + i + "]//tr[1]//td[3]/div/span";
            String rowData = driver.findElement(By.xpath(rowXpath)).getText();
            /*System.out.println("Row Data:  " + rowData.toLowerCase());
            System.out.println("Tenant Value:  " + tenantValue);*/
            Assert.assertTrue(rowData.toLowerCase().contains(tenantValue));
        }
        System.out.println("Search Results correctly displayed");
    }

    public void searchOutlet(String outletValue) {
        searchFunction(outletValue);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(search_Results_Page_Title)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(getSearch_result_Table_Rows)));
        int Row_count = driver.findElements(getSearch_result_Table_Rows).size();
               for (int j = 1; j <= Row_count; j++) {
            String outletRowXpath = "//div[@class='table-responsive']//tbody[" + j + "]//tr[2]//td[3]//div[1]/span";
            if (driver.findElements(By.xpath(outletRowXpath)).size() != 0) {
                String outletRowData = driver.findElement(By.xpath(outletRowXpath)).getText();
                Assert.assertTrue(outletRowData.toLowerCase().contains(outletValue));
            }
            else {
                System.out.println("No outlets found");
            }
        }

        System.out.println("Search Results correctly displayed");
    }

    // Verify outlet details in summary page
    public void verifyOutlet(String OutletName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_expand));
        scrollIntoView(driver.findElement(btn_expand));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(btn_expand))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//SPAN[text()='"+OutletName+"']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody[1]/tr[2]/td[7]")));
        String Outletname = driver.findElement(txt_outletNeme).getText();
        Assert.assertEquals(Outletname, OutletName);
        System.out.println("Created Outlet name verified : " + OutletName);
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
