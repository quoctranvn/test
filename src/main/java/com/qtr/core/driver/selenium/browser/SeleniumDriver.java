package com.qtr.core.driver.selenium.browser;

import org.openqa.selenium.WebDriver;

public interface SeleniumDriver {

    WebDriver createDriver();

    void setDriverOptions(Object options);
}
