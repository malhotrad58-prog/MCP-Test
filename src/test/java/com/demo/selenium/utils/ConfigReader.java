package com.demo.selenium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";

    public static String getBaseUrl() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IOException("Unable to find " + CONFIG_FILE + " in the classpath.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read config.properties", e);
        }

        return properties.getProperty("base.url");
    }
}
