package com.qtr.core.driver.selenium;

import com.qtr.core.driver.selenium.browser.ChromeConfiguration;
import com.qtr.core.driver.selenium.browser.RemoteConfiguration;
import com.qtr.core.logger.Log4jFactory;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DriverFactory {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());
    private static DriverFactory webDriverFactory;
    private LinkedMap<Long, WebDriver> driverStorage = new LinkedMap<>();
    private WebDriver webDriver;

    private DriverFactory() { }

    public static synchronized DriverFactory instance() {
        if(webDriverFactory == null)
            webDriverFactory = new DriverFactory();
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
            webDriver = new RemoteConfiguration().createDriver(host);
        } else {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                    webDriver = new ChromeConfiguration().createDriver();
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
            log.info("*****No existing thread id\n");
            return null;
        }

        if (driverStorage.containsKey(Thread.currentThread().getId()))
            return driverStorage.get(Thread.currentThread().getId());

        return driverStorage.getValue(0);
    }

    public void disposeWebDriver() {
        log.info("\nClose current web-driver\n");
        if (driverStorage.containsKey(Thread.currentThread().getId())) {
            driverStorage.get(Thread.currentThread().getId()).quit();
        }
        driverStorage.remove(Thread.currentThread().getId());
    }

    public void disposeAllDriver() {
        log.info("\nClose all web-driver\n");
        for (Map.Entry<Long, WebDriver> driver : driverStorage.entrySet()) {
            WebDriver value = driver.getValue();
            value.quit();
        }
    }

    public void takeScreenshot(String imagePath) {
        TakesScreenshot scrShot =((TakesScreenshot)getWebDriver());
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File destFile=new File(imagePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            log.error("Take screenshot has error", e);
        }
    }
}

