package com.qtr.core.listener.TestNG;

import com.qtr.core.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!3\n");
        System.out.print(Thread.currentThread().getId());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        int countFailed = Log4jFactory.instance().getFailedStorage(Thread.currentThread().getId());
        System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!4\n");
        System.out.print(countFailed);
        if(countFailed > 0)
            log.info("\n----- TEST PASSED: " + iTestResult.getName());
        else {
            iTestResult.setStatus(ITestResult.FAILURE);
            log.error("\n----- TEST FAILED: " + iTestResult.getName());
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("\n----- TEST FAILED: " + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("\n----- TEST SKIPPED: " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.error("\n----- TEST FAILED WITH SUCCESS PERCENTAGE: " + iTestResult.getName());
    }

    @Override
    public void onStart(ITestContext iTestContext) { log.info("\n----- TEST STARTED: " + iTestContext.getName()); }

    @Override
    public void onFinish(ITestContext iTestContext) { log.info("\n----- TEST ENDED: " + iTestContext.getName()); }

}
