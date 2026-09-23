package com.demo.selenium.utils;

public class LoginData {
    private String url;
    private String username;
    private String password;
    private String scenario;
    private String expectedMessage;

    public LoginData() {
    }

    public LoginData(String url, String username, String password, String scenario, String expectedMessage) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.scenario = scenario;
        this.expectedMessage = expectedMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public void setExpectedMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }
}
