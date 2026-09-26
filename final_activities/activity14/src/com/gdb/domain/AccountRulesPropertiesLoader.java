package com.gdb.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AccountRulesPropertiesLoader {
    private Properties properties = new Properties();

    public AccountRulesPropertiesLoader(String configPath) {
        loadProperties(configPath);
    }

    private void loadProperties(String configPath) {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(configPath);
            if (input == null) {
                File file = new File(configPath);
                if (file.exists()) {
                    input = new FileInputStream(file);
                }
            }
            if (input == null) {
                System.out.println("Warning: properties file not found: " + configPath);
                return;
            }
            try (InputStream stream = input) {
                properties.load(stream);
            }
        } catch (Exception e) {
            System.out.println("Warning: unable to load properties: " + e.getMessage());
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) return defaultValue;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
