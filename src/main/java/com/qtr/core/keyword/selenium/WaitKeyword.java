package com.qtr.core.keyword.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.qtr.core.driver.selenium.DriverFactory.instance;

public class WaitKeyword {

    public static void waitForPageLoad(int timeout) {
        WebDriver driver = instance().getWebDriver();
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(pageLoadCondition);
    }

    public static boolean waitForElementVisible(WebElement webElement, int waitTime){
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementStaleness(WebElement webElement, int waitTime){
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.stalenessOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementPresent(WebElement webElement, int waitTime){
        Long currentTime = System.currentTimeMillis();
        Long maxTime = currentTime + waitTime * 1000;
        boolean check = false;
        while (currentTime < maxTime) {
            if(check)
                break;
            check = webElement.isDisplayed();
            currentTime = System.currentTimeMillis();
        }
        return check;
    }

    public static Boolean waitForElementNotVisible(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForTextPresentInElement(WebElement webElement, String text, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
