package com.qtr.core.exception;

public class StepFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StepFailedException(String message) {
        super(message);
    }

    public StepFailedException(Throwable throwable) {
        super(throwable);
    }

    public StepFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public StepFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
