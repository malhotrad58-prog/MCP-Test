# Selenium Page Object Model Demo

This project demonstrates a Java + Selenium + TestNG login flow using the Page Object Model (POM) design pattern. The application URL is kept in a config file, while the username/password data is stored in an Excel file.

## Included scenarios
- Positive login flow with valid credentials
- Negative login flow with invalid credentials
- Negative login flow when the username is blank

## Configuration and data files
- [src/test/resources/config.properties](src/test/resources/config.properties): stores the application base URL
- [src/test/resources/login_credentials.xlsx](src/test/resources/login_credentials.xlsx): stores username, password, scenario, and expected error text

The Excel sheet contains these columns:
- USERNAME
- PASSWORD
- SCENARIO
- EXPECTED_MESSAGE

## Project structure
- [src/test/java/com/demo/selenium/pages/BasePage.java](src/test/java/com/demo/selenium/pages/BasePage.java): common page helper methods such as waits and locator handling
- [src/test/java/com/demo/selenium/pages/LoginPage.java](src/test/java/com/demo/selenium/pages/LoginPage.java): login page actions and validation checks
- [src/test/java/com/demo/selenium/pages/InventoryPage.java](src/test/java/com/demo/selenium/pages/InventoryPage.java): inventory page assertion after successful sign-in
- [src/test/java/com/demo/selenium/utils/ConfigReader.java](src/test/java/com/demo/selenium/utils/ConfigReader.java): reads the base URL from config.properties
- [src/test/java/com/demo/selenium/utils/ExcelDataReader.java](src/test/java/com/demo/selenium/utils/ExcelDataReader.java): reads username/password rows from Excel and adds the base URL from config
- [src/test/java/com/demo/selenium/utils/LoginData.java](src/test/java/com/demo/selenium/utils/LoginData.java): model class for each Excel row
- [src/test/java/com/demo/selenium/tests/LoginTests.java](src/test/java/com/demo/selenium/tests/LoginTests.java): positive and negative login test cases driven by Excel data

## Run the demo
```bash
mvn test
```

## Notes
This example uses the SauceDemo login page as the default target, and the app URL can be changed in the config file without changing the Excel workbook.
