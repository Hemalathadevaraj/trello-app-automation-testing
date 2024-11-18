package com.trello.assignment.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Reads properties from config.properties file
 */
public class ConfigProperties {
    private static Properties properties;

    public static void initProperties() {
        properties = new Properties();
        initializeProperties();
    }

    public static String getPropertyValue(String property) {
        return properties.getProperty(property);
    }

    private static void initializeProperties() {
        try {
            properties.load(ConfigProperties.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(String.format("IOException during initialising properties %s", e.getMessage()));
        }
    }
}
