package com.qtr.core.listeners;

import com.qtr.core.config.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestNGSuiteListener implements ISuiteListener {

    Logger log = LoggerFactory.instance().createClassLogger(getClass());

    @Override
    public void onStart(ISuite iSuite) { log.info("\n----- START SUITE: " + iSuite.getName()); }

    @Override
    public void onFinish(ISuite iSuite) { log.info("\n----- END SUITE: " + iSuite.getName());
    }
}
