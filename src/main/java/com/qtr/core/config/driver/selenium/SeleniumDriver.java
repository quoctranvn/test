package com.qtr.core.config.driver.selenium;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface SeleniumDriver {

    WebDriver createDriver() throws MalformedURLException;

    void setDriverOptions(Object options);
}
