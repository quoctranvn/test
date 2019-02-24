package com.qtr.core.exception;

import static com.qtr.core.logger.Log4jFactory.*;

public class ExceptionHandler {

    public static void exceptionHandler(Throwable throwable, FailureHandling failureHandling) {
        switch (failureHandling) {
            case CONTINUE_ON_FAILURE:
                logFailedAndContinue(throwable);
                break;
            case WARNING:
                logWarning(throwable);
                break;
            default:
                logFailedAndStop(throwable);
        }
    }
}
