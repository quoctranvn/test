package com.qtr.core.listener.TestNG;

import com.qtr.core.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @Override
    public void onStart(ISuite iSuite) { log.info("\n----- SUITE STARTED: " + iSuite.getName() + "\n"); }

    @Override
    public void onFinish(ISuite iSuite) {
        log.info("\n");
        log.info("\n----- SUITE ENDED: " + iSuite.getName());
    }
}
