package com.qtr.core.keyword.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.qtr.core.configuration.WaitTime.getMaxWaitTime;
import static com.qtr.core.configuration.WaitTime.getMinWaitTime;
import static com.qtr.core.driver.selenium.DriverFactory.instance;
import static com.qtr.core.keyword.selenium.WaitKeyword.waitForElementPresent;
import static com.qtr.core.keyword.selenium.WaitKeyword.waitForElementVisible;

public class ActionKeyword {

    private static int minWaitTime = getMinWaitTime();
    private static int maxWaitTime = getMaxWaitTime();

    public static List<String> getListText(List<WebElement> listElement) {
        List<String> listText = new ArrayList<String >();
        for(WebElement e: listElement) {
            listText.add(e.getText().trim());
        }
        return listText;
    }

    public static List<String> getListAttributeValues(List<WebElement> listElement, String attributeName) {
        List<String> listAttributeValue = new ArrayList<>();
        for(WebElement e: listElement) {
            listAttributeValue.add(e.getAttribute(attributeName));
        }
        return listAttributeValue;
    }

    public static void setText(WebElement webElement, String value) {
        if(waitForElementVisible(webElement, maxWaitTime * 3))
            webElement.clear();
        webElement.sendKeys(value);
    }

    public static String getText(WebElement webElement, int timeout) {
        waitForElementVisible(webElement, timeout);
        return webElement.getText();
    }

    public static void clickJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static void moveAndClick(WebElement webElement) {
        waitForElementVisible(webElement, minWaitTime);
        Actions action = new Actions(instance().getWebDriver());
        action.moveToElement(webElement).click().build().perform();
    }

    public static void moveAndClickJS(WebElement webElement) {
        waitForElementPresent(webElement, minWaitTime);
        Actions action = new Actions(instance().getWebDriver());
        action.moveToElement(webElement).perform();
        clickJS(webElement);
    }

    public static void clickByText(String textValue, Boolean isExactly) {
        String xpath;
        if(isExactly)
            xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), minWaitTime);
        WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        moveAndClick(e);
    }
}
