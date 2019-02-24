package com.qtr.core.configuration;

import java.util.Properties;

import static com.qtr.core.helper.FileHelper.getProperties;

public class WaitTime {

    private static Properties prop = getProperties("wait");

    public static int getMaxWaitTime() {
        return Integer.valueOf(prop.getProperty("maxWaitTime"));
    }

    public static int getMinWaitTime() {
        return Integer.valueOf(prop.getProperty("minWaitTime"));
    }
}
