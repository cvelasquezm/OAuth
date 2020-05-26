package com.mrc.oauth.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    public final static PropertiesManager INSTANCE = new PropertiesManager();
    private final String NAME_PROPERTIES = "app.properties";
    private Properties properties = new Properties();

    private PropertiesManager() {
        synchronized (this){
            if (INSTANCE == null){
                try {
                    properties.load(ClassLoader.getSystemResourceAsStream(NAME_PROPERTIES));
                } catch (IOException e) {
                    System.out.println("error loading file properties");
                }
            }
        }
    }

    public synchronized String getProperty(String key) {
        return properties.getProperty(key);
    }
}
