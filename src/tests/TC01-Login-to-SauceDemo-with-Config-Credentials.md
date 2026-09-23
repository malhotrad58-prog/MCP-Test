# Test Case: TC01 - Login to SauceDemo with Config Credentials

## Requirement Reference
- Jira Test Case: TC01
- Story / Link: https://malhotrad58.atlassian.net/jira/software/projects/SCRUM/apps/3feb7ced-1450-4676-aded-099c99bf534b/2baaeb69-15ac-4955-8eb6-e346aa1567aa#/v2/testCase/7795166?projectId=10000
- Application URL: https://www.saucedemo.com/
- Credentials source: src/test/resources/config.properties + src/test/resources/login_credentials.xlsx

## Objective
Verify that a valid user can log in to SauceDemo using the configured credentials and that the application redirects the user to the inventory page.

## Preconditions
- The application URL is configured in `src/test/resources/config.properties`.
- The login credentials are available in `src/test/resources/login_credentials.xlsx`.
- The browser is available and can launch in headless mode.

## Test Steps
1. Open the SauceDemo login page using the configured base URL.
2. Read the valid username and password from the Excel data source.
3. Enter the username in the Username field.
4. Enter the password in the Password field.
5. Click the Login button.
6. Wait for the inventory page to load.

## Expected Result
- The login is successful.
- The user is redirected to the Products page.
- The page title displays `Products`.
- No error message is displayed.

## Test Data
- Username: standard_user
- Password: secret_sauce

## Priority
- High

## Test Type
- Functional

## Automation Status
- Ready for Selenium automation
