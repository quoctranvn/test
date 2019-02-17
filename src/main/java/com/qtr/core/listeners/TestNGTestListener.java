package com.qtr.core.listeners;

import com.qtr.core.config.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGTestListener implements ITestListener {

    Logger log = LoggerFactory.instance().createClassLogger(getClass());

    @Override
    public void onTestStart(ITestResult iTestResult) { }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("\n----- TEST PASSED: " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.fatal("\n----- TEST FAILED: " + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("\n----- TEST SKIPPED: " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.fatal("\n----- TEST FAILED WITH SUCCESS PERCENTAGE: " + iTestResult.getName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("\n----- START TEST: " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("\n----- FINISH TEST: " + iTestContext.getName());
    }

}
