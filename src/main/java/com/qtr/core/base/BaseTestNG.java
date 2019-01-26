package com.qtr.core.base;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import com.qtr.core.listeners.TestNGExecutionListener;
import org.testng.annotations.*;

@Listeners({TestNGExecutionListener.class})
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
