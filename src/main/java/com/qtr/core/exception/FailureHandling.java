package com.qtr.core.exception;

public enum FailureHandling {
    STOP_ON_FAILURE, CONTINUE_ON_FAILURE, WARNING;

    private FailureHandling() {}
}
