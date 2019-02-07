package com.qtr.core.config.driver.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;

public class ExecutableDriver {

    private final String browserName;
    private String driverVersion = System.getProperty("driverVersion");

    public ExecutableDriver(String browserName) {
        this.browserName = browserName;
        setDriverExecutable();
    }

    private void setDriverExecutable() {
        //Set directory that contains webdrivers to .\drivers
        WebDriverManager.config().setTargetPath("drivers");

        String pathToDriver;
        //Initiate new webdriver instance base on browserName
        switch(browserName.toLowerCase().trim()) {
            case "chrome":
                if (driverVersion == null) {
                    WebDriverManager.chromedriver().setup();
                    pathToDriver = WebDriverManager.chromedriver().getBinaryPath();
                } else {
                    WebDriverManager.chromedriver().version(driverVersion).setup();
                    pathToDriver = WebDriverManager.chromedriver().getBinaryPath();
                }
                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, pathToDriver);
                break;
        }
    }

}
