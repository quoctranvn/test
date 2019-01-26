package com.qtr.core.listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGExecutionListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) { System.out.println("\n------ START TEST ------\n"); }

    @Override
    public void onTestSuccess(ITestResult result) { System.out.println("\n------ TEST PASSED ------\n"); }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("\n------ TEST FAILED ------\n");
    }

}
