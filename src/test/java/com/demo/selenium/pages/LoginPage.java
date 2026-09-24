package com.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("https://www.saucedemo.com/");
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        waitForVisible(usernameInput).clear();
        waitForVisible(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForVisible(passwordInput).clear();
        waitForVisible(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        waitForClickable(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return waitForVisible(errorMessage).getText();
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorMessage);
    }

    public InventoryPage loginWithValidCredentials(String username, String password) {
        login(username, password);
        return new InventoryPage(driver);
    }
}
