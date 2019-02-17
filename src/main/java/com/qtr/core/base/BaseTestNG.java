package com.qtr.core.base;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import com.qtr.core.config.report.Reporter;
import com.qtr.core.listeners.TestNGTestListener;
import com.qtr.core.listeners.TestNGSuiteListener;
import org.testng.annotations.*;

@Listeners({TestNGTestListener.class, TestNGSuiteListener.class, Reporter.class})
public class BaseTestNG {

    @BeforeClass
    @Parameters("browserName")
    public void setUpTestClass(@Optional("chrome")String browserName) {
        WebDriverFactory.instance().createWebDriver(browserName);
    }

    @AfterClass
    public void tearDownTestClass() {
        WebDriverFactory.instance().disposeWebDriver();
    }
}
