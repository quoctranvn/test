package com.qtr.core.keyword.selenium;

import com.qtr.core.driver.selenium.DriverFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.qtr.core.driver.selenium.DriverFactory.instance;

public class PageKeyword {

    public static void openURL(String url) {
        instance().getWebDriver().get(url);
    }

    public static void backToPreviousURL(String previousURL, int waitTime) {
        String newURL;
        boolean check = false;
        for (int i = 1; i < 5; i++) {
            if(check)
                break;
            else {
                instance().getWebDriver().navigate().back();
                newURL = instance().getWebDriver().getCurrentUrl();
                check = previousURL.equals(newURL);
            }
        }
    }

    public static void closeAndSwitchToWindowHandle(String windowsHandle) {
        Set<String> setWindowHandles = DriverFactory.instance().getWebDriver().getWindowHandles();
        for (String w: setWindowHandles) {
            if(!w.equals(windowsHandle)) {
                DriverFactory.instance().getWebDriver().switchTo().window(w);
                DriverFactory.instance().getWebDriver().close();
            }
        }
        DriverFactory.instance().getWebDriver().switchTo().window(windowsHandle);
    }

    public static void switchToAnotherWindow() {
        Set<String> setWindowHandles = DriverFactory.instance().getWebDriver().getWindowHandles();
        String currentWindow = DriverFactory.instance().getWebDriver().getWindowHandle();
        for (String w: setWindowHandles) {
            if(!w.equals(currentWindow)) {
                DriverFactory.instance().getWebDriver().switchTo().window(w);
            }
        }
    }

    public static void verifyPageTitleContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.titleContains(textValue));
    }

    public static void verifyPageURLContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.urlContains(textValue));
    }
}
