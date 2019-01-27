package com.qtr.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileHelper {

    public FileHelper() { }

    public static Properties getProperties(String propertyFileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = FileHelper.class.getClassLoader().getResourceAsStream(propertyFileName + ".properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                    return prop;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}