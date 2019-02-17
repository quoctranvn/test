package com.qtr.core.config.driver.selenium;

import com.qtr.core.config.driver.selenium.browser.ChromeDriverConfig;
import com.qtr.core.config.driver.selenium.browser.RemoteDriverConfig;
import com.qtr.core.config.logger.LoggerFactory;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class WebDriverFactory {

    Logger log = LoggerFactory.instance().createClassLogger(getClass());
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

