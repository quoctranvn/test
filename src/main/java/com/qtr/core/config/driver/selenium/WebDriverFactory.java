package com.qtr.core.config.driver.selenium;

import com.qtr.core.config.driver.selenium.browser.ChromeDriverConfig;
import com.qtr.core.config.driver.selenium.browser.RemoteDriverConfig;
import org.apache.commons.collections4.map.LinkedMap;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class WebDriverFactory {

    private static WebDriverFactory webDriverFactory;
    private LinkedMap<Long, WebDriver> driverStorage = new LinkedMap<>();
    private WebDriver webDriver;

    private WebDriverFactory() { }

    public static synchronized WebDriverFactory instance() {
        if(webDriverFactory == null)
            webDriverFactory = new WebDriverFactory();
        return webDriverFactory;
    }

    public void createWebDriver(String browserName) {
        String host = System.getProperty("seleniumHubHost");
        String browserInput = System.getProperty("browser");
        if (host == null)
            host = "none";
        if (browserInput != null)
            browserName = browserInput; // Select browser input from cmd
        if (!host.equals("none")) {
            webDriver = new RemoteDriverConfig().createDriver(host);
        } else {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                    webDriver = new ChromeDriverConfig().createDriver();
                    break;
            }
        }
        setDriverStorage(webDriver);
    }

    private void setDriverStorage(WebDriver webDriver) {
        driverStorage.put(Thread.currentThread().getId(), webDriver);
    }

    public WebDriver getWebDriver() {
        if (driverStorage.size() == 0) {
            System.out.println("*****No existing thread id\n");
            return null;
        }

        if (driverStorage.containsKey(Thread.currentThread().getId()))
            return driverStorage.get(Thread.currentThread().getId());

        return driverStorage.getValue(0);
    }

    public void disposeWebDriver() {
        System.out.print("\nClose current web-driver\n");
        if (driverStorage.containsKey(Thread.currentThread().getId())) {
            driverStorage.get(Thread.currentThread().getId()).quit();
        }
        driverStorage.remove(Thread.currentThread().getId());
    }

    public void disposeAllDriver() {
        System.out.print("\nClose all web-driver\n");
        for (Map.Entry<Long, WebDriver> driver : driverStorage.entrySet()) {
            WebDriver value = driver.getValue();
            value.quit();
        }
    }
}

