package com.ancon.automation.pages;

import com.ancon.automation.utils.CommonClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TenantSummary extends CommonClass {
    private WebDriver driver;
    private WebDriverWait wait;
    //search Tenant
    private By search_field = By.xpath("//div[@class='container___2Ufnl']/input");
    private By search_Results_Page_Title = By.xpath("//h1[contains(text(),'Search Tenants or Outlets')]");
    private By search_No_Results_Found = By.xpath("//h2[contains(text(),'No matching results found')]");
    private By getSearch_result_Table_Rows = By.xpath("//table[@class='ancon-table table']//tbody");
    private By search_Button = By.xpath("//div[@class='search-button']");
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
            } else {
                System.out.println("No outlets found");
            }
        }

        System.out.println("Search Results correctly displayed");
    }


}
