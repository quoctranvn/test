package com.qtr.core.config.driver.selenium.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverConfig {

    private DesiredCapabilities capabilities;
    private final String host = System.getProperty("seleniumHubHost");
    private final String browserName = System.getProperty("browser").toLowerCase();

    public WebDriver createDriver() {
        try {
            initRemoteCapabilities(browserName);
            URL gridUrl = new URL(this.host);
            WebDriver webDriver = new RemoteWebDriver(gridUrl, this.capabilities);
            return webDriver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initRemoteCapabilities(String browserName) {
        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                this.capabilities = DesiredCapabilities.chrome();
                this.capabilities.setAcceptInsecureCerts(true);
                this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                break;
        }
    }
}
