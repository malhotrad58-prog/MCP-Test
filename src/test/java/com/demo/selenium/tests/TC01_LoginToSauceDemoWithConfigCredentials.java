package com.demo.selenium.tests;

import com.demo.selenium.pages.InventoryPage;
import com.demo.selenium.pages.LoginPage;
import com.demo.selenium.utils.ConfigReader;
import com.demo.selenium.utils.ExcelDataReader;
import com.demo.selenium.utils.LoginData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TC01_LoginToSauceDemoWithConfigCredentials extends BaseTest {

    @Test
    public void TC01_LoginToSauceDemoWithConfigCredentials() throws IOException {
        List<LoginData> validLogins = ExcelDataReader.readValidLogins();
        Assert.assertFalse(validLogins.isEmpty(), "TC01: No valid login data found in Excel.");

        LoginData validLogin = validLogins.get(0);
        String baseUrl = ConfigReader.getBaseUrl();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        InventoryPage inventoryPage = loginPage.loginWithValidCredentials(validLogin.getUsername(), validLogin.getPassword());

        Assert.assertTrue(inventoryPage.isProductsPageDisplayed(),
                "TC01: Inventory page should be displayed after successful login.");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products",
                "TC01: The page title should be Products after login.");
    }
}
