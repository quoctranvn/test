package com.qtr.core.exception;

import com.qtr.core.logger.Log4jFactory;

public class ExceptionHandler {

    public static void exceptionHandler(Throwable throwable, FailureHandling failureHandling) {
        switch (failureHandling) {
            case CONTINUE_ON_FAILURE:
                Log4jFactory.instance().logFailedAndContinue(throwable);
                break;
            case WARNING:
                Log4jFactory.instance().logWarning(throwable);
                break;
            default:
                Log4jFactory.instance().logFailedAndStop(throwable);
        }
    }
}
