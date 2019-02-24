package com.qtr.core.driver.selenium.browser;

import com.qtr.core.driver.selenium.ConfigDriver;
import com.qtr.core.driver.selenium.SetupDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfiguration extends ConfigDriver implements SeleniumDriver {

    private ChromeOptions options;

    private ChromeOptions getOptions() {
        if (options == null) {
            options = new ChromeOptions();
            options.addArguments("--disable-gpu");
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        new SetupDriver("chrome");
        WebDriver driver = new ChromeDriver(getOptions());
        manage(driver);
        return driver;
    }

    @Override
    public void setDriverOptions(Object options) {
        this.options = (ChromeOptions) options;
    }

}
