package com.qtr.core.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) { }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\n------ TEST IS PASSED: " + iTestResult.getName() + " ------\n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("\n------ TEST IS FAILED: " + iTestResult.getName() + " ------\n");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("\n------ TEST IS SKIPPED: " + iTestResult.getName() + " ------\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("\n------ TEST IS SKIPPED WITH SUCCESS PERCENTAGE: " + iTestResult.getName() + " ------\n");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("\n------ START TEST: " + iTestContext.getName() + " ------\n");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("\n------ FINISH TEST: " + iTestContext.getName() + " ------\n");
    }

}
