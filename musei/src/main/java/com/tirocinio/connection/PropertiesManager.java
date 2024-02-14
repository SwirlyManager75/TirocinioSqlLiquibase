package com.tirocinio.connection;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesManager {
    
    private static PropertiesManager instance = null;
    private Properties properties;

    private PropertiesManager() {
        loadProperties();
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            synchronized (PropertiesManager.class) {
                if (instance == null) {
                    instance = new PropertiesManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() 
    {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("C:\\Users\\Swirl\\OneDrive\\Desktop\\Tirocinio\\TirocinioSqlLiquibase\\musei\\src\\main\\java\\com\\tirocinio\\connection\\config.properties")) 
        {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

    public String getUrl() {
        return properties.getProperty("URL");
    }

    public String getUsername() {
        return properties.getProperty("USER");
    }

    public String getPassword() {
        return properties.getProperty("PASSWORD");
    }
}