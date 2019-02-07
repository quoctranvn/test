package com.qtr.core.listeners;

import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestNGSuiteListener implements ISuiteListener {

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("\n------ START TEST SUITE: " + iSuite.getName() + " ------\n");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println("\n------ END TEST SUITE: " + iSuite.getName() + " ------\n");
    }
}
