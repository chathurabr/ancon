package com.ancon.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CommonScreenshot extends DriverFactory {
   // private static WebDriver driver;
    static WebDriver wdd = DriverFactory.driver;

    public static WebDriver takeSnapshot(ITestResult testResult) throws IOException {

        if (testResult.getStatus() == ITestResult.FAILURE) {
            //	System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = System.getProperty("user.dir");
            FileUtils.copyFile(scrFile, new File(filePath + "\\src\\main\\java\\com\\ancon\\automation\\screenshots\\" + testResult.getName() + "-" + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        return wdd;
    }
}
