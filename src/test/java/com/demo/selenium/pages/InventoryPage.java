package com.demo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private final By pageTitle = By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return waitForVisible(pageTitle).getText();
    }

    public boolean isProductsPageDisplayed() {
        return isDisplayed(pageTitle) && getPageTitle().contains("Products");
    }
}
