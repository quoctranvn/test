package com.qtr.core.logger;

import com.qtr.core.exception.StepFailedException;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jFactory {
    private static Log4jFactory log4jFactory;
    private LinkedMap<Long, Integer> failedStorage = new LinkedMap<>();
    Logger log = LogManager.getLogger(Log4jFactory.class);

    private Log4jFactory() {
        Log4jConfiguration.instance();
    }

    public static synchronized Log4jFactory instance() {
        if (log4jFactory == null)
            log4jFactory = new Log4jFactory();
        return log4jFactory;
    }

    public static Logger createClassLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }

    public void logWarning(Throwable throwable) {
        Boolean check = throwable instanceof Error;
        if(!check)
            log.warn(ExceptionUtils.getStackTrace(throwable));
        else
            log.error(ExceptionUtils.getStackTrace(throwable));
    }

    public void logFailedAndContinue(Throwable throwable) {
        instance().setFailedStorage();
        log.error(ExceptionUtils.getStackTrace(throwable));
    }

    public void logFailedAndStop(Throwable throwable){
        throw new StepFailedException(throwable);
    }

    private void setFailedStorage() { failedStorage.put(Thread.currentThread().getId(), 1); }

    public int getFailedStorage(Long l) {
        int countFailed = 0;
        if(failedStorage.containsKey(l))
            countFailed = failedStorage.get(l);
        return countFailed;
    }
}