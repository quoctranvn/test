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
        String targetPath = "drivers";
        String pathToDriver;
        //Initiate new web-driver instance base on browserName
        switch(browserName.toLowerCase().trim()) {
            case "chrome":
                WebDriverManager.chromedriver().targetPath(targetPath);
                if (driverVersion == null)
                    WebDriverManager.chromedriver().setup();
                else WebDriverManager.chromedriver().version(driverVersion).setup();
                pathToDriver = WebDriverManager.chromedriver().getBinaryPath();
                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, pathToDriver);
                break;
        }
    }

}
