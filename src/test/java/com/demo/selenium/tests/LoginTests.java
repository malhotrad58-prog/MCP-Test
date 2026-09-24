package com.demo.selenium.tests;

import com.demo.selenium.pages.InventoryPage;
import com.demo.selenium.pages.LoginPage;
import com.demo.selenium.utils.ExcelDataReader;
import com.demo.selenium.utils.LoginData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() throws IOException {
        return ExcelDataReader.readValidLogins().stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() throws IOException {
        return ExcelDataReader.readInvalidLogins().stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "validLoginData")
    public void shouldLoginSuccessfullyWithValidCredentialsFromExcel(LoginData loginData) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(loginData.getUrl());

        InventoryPage inventoryPage = loginPage.loginWithValidCredentials(loginData.getUsername(), loginData.getPassword());

        Assert.assertTrue(inventoryPage.isProductsPageDisplayed(), "User should be redirected to the inventory page after successful login.");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test(dataProvider = "invalidLoginData")
    public void shouldShowErrorForInvalidCredentialsFromExcel(LoginData loginData) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(loginData.getUrl());

        loginPage.login(loginData.getUsername(), loginData.getPassword());

        Assert.assertTrue(loginPage.isErrorVisible(), "Error message should be visible for invalid login attempt.");
        Assert.assertTrue(loginPage.getErrorMessage().contains(loginData.getExpectedMessage()),
                "Expected error message does not match the actual validation message.");
    }
}
