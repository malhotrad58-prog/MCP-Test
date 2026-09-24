package com.demo.selenium.tests;

import com.demo.selenium.utils.ReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("PASS: " + result.getMethod().getMethodName() + " - Test passed successfully.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("FAIL: " + result.getMethod().getMethodName() + " - " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("SKIP: " + result.getMethod().getMethodName() + " - Test skipped.");
        }

        ReportManager.recordResult(result);

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void generateReport() {
        ReportManager.generateHtmlReport();
    }
}
