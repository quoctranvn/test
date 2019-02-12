package com.qtr.core.config.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RollingFileManager;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerManager {
    final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    final Configuration config = ctx.getConfiguration();
    private String logFolderPath;
    private PatternLayout layout;
    private SizeBasedTriggeringPolicy policy;
    private DefaultRolloverStrategy strategy;
    private RollingFileAppender fileAppender;
    private ConsoleAppender consoleAppender;
    private String fileName;
    private static LoggerManager instance;

    private LoggerManager() {
        initLogFolder();
        configLog4j();
    }

    public static synchronized LoggerManager instance() {
        if (instance == null) {
            instance = new LoggerManager();
        }
        return instance;
    }

    public void configLog4j() {
        initLogConfig();
        createConsoleAppender();
        createFileAppender();
        startLogConfig();
    }

    private void initLogFolder() {
        String currentPath = System.getProperty("user.dir");
        File logFolder = new File(currentPath + File.separator + "logs");
        logFolder.mkdirs();
        logFolderPath = logFolder.getAbsolutePath();
        fileName =  logFolderPath + File.separator +
                new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss'.log'").format(new Date());
    }

    private void initLogConfig() {
        layout = PatternLayout.newBuilder().withConfiguration(config)
                .withPattern(PatternLayout.SIMPLE_CONVERSION_PATTERN).build();
        strategy = DefaultRolloverStrategy.createStrategy("100", "0", null, null,
                null, false, config);
        policy = SizeBasedTriggeringPolicy.createPolicy("10MB");
        RollingFileManager fileManager = RollingFileManager.getFileManager(fileName, fileName,
                false, false, policy, strategy, null, layout, 128,
                false, false, null, null, null, config);
        policy.initialize(fileManager);
    }

    private void createFileAppender() {
        fileAppender = RollingFileAppender.newBuilder().setConfiguration(config)
                .withFileName(fileName).withFilePattern(fileName).withAppend(true)
                .withName("File").withBufferedIo(true).withBufferSize(512).withImmediateFlush(true).withPolicy(policy)
                .withStrategy(strategy).withLayout(layout).withFilter(null).withIgnoreExceptions(false)
                .withAdvertise(false).withAdvertiseUri("false").build();
    }

    private void createConsoleAppender() {
        consoleAppender = ConsoleAppender.newBuilder().setConfiguration(config).setTarget(ConsoleAppender.Target.SYSTEM_OUT)
                .withName("Console").withLayout(layout).withBufferedIo(true).withImmediateFlush(true).withBufferSize(512).build();
    }

    private void startLogConfig() {
        // Create file appender ref
        fileAppender.start();
        config.addAppender(fileAppender);
        AppenderRef fileRef = AppenderRef.createAppenderRef("File", Level.ALL, null);
        AppenderRef[] fileRefs = new AppenderRef[] { fileRef };
        // Create console appender ref
        consoleAppender.start();
        config.addAppender(consoleAppender);
        AppenderRef consoleRef = AppenderRef.createAppenderRef("Console", Level.ALL, null);
        AppenderRef[] consoleRefs = new AppenderRef[] { consoleRef };
        // Create new logger config
        LoggerConfig loggerConfig = LoggerConfig.createLogger(true, Level.ALL, LogManager.ROOT_LOGGER_NAME,
                "true", consoleRefs, null, config, null);
        loggerConfig.addAppender(consoleAppender, Level.ALL, null);
        loggerConfig.addAppender(fileAppender, Level.ALL, null);
        config.addLogger(LogManager.ROOT_LOGGER_NAME, loggerConfig);
        ctx.updateLoggers();
    }
}
