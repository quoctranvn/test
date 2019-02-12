package com.qtr.core.config.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerFactory {
    private static LoggerFactory loggerFactory;

    private LoggerFactory() {
        LoggerManager.instance();
    }

    public static synchronized LoggerFactory instance() {
        if (loggerFactory == null)
            loggerFactory = new LoggerFactory();
        return  loggerFactory;
    }

    public Logger createClassLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }
}
