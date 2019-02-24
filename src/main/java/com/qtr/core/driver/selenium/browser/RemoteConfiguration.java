package com.qtr.core.driver.selenium.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteConfiguration {

    private DesiredCapabilities capabilities;

    public WebDriver createDriver(String host) {
        try {
            String browserName = System.getProperty("browser").toLowerCase();
            initRemoteCapabilities(browserName);
            URL gridUrl = new URL("http://" + host + ":4444/wd/hub");
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
//                this.capabilities.setAcceptInsecureCerts(true);
//                this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                break;
        }
    }
}
