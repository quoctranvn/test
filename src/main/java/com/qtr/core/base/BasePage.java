package com.qtr.core.base;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.qtr.core.config.driver.selenium.WebDriverFactory.instance;

public class BasePage {

    private static int minWaitTime = 1;
    private static int maxWaitTime = 3;

    public void openURL(String url) {
        instance().getWebDriver().get(url);
    }

    public static void clickJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static void moveAndClick(WebElement webElement) {
        Actions action = new Actions(instance().getWebDriver());
        action.moveToElement(webElement).click().build().perform();
        waitForPageLoad(minWaitTime);
    }

    public static void moveAndClickJS(WebElement webElement) {
        Actions action = new Actions(instance().getWebDriver());
        action.moveToElement(webElement).perform();
        clickJS(webElement);
        waitForPageLoad(minWaitTime);
    }

    public static void clickByText(String textValue, Boolean isExactly) {
        String xpath;
        if(isExactly)
            xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        WebElement e = instance().getWebDriver().findElement(By.xpath(xpath));
        moveAndClick(e);
    }

    public static void setText(WebElement webElement, String value) {
        if(waitForElementVisible(webElement, minWaitTime))
            webElement.clear();
            webElement.sendKeys(value);
    }

    public static String getText(WebElement webElement, int timeout) {
        waitForElementVisible(webElement, timeout);
        return webElement.getText();
    }

    public static void verifyElementPresent(WebElement webElement) {
        Assert.assertEquals(webElement.isDisplayed(), true, "\nWeb element " + webElement  + " is not displayed");
    }

    public static void verifyElementVisible(WebElement webElement, int waitTime){
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void verifyElementNotVisible(WebElement webElement, int waitTime){
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void verifyTextPresent(String textValue, Boolean isExactly) {
        String xpath;
        if(isExactly)
            xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        WebElement e = instance().getWebDriver().findElement(By.xpath(xpath));
        verifyElementPresent(e);
    }

    public static void verifyPageTitleContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.titleContains(textValue));
    }

    public static void verifyPageURLContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.urlContains(textValue));
    }

    public static List<String> getListText(List<WebElement> listElement) {
        List<String> listText = new ArrayList<String >();
        for(WebElement e: listElement) {
            listText.add(e.getText().trim());
        }
        return listText;
    }

    public static List<String> getListAttributeValue(List<WebElement> listElement, String attributeName) {
        List<String> listAttributeValue = new ArrayList<>();
        for(WebElement e: listElement) {
            listAttributeValue.add(e.getAttribute(attributeName));
        }
        return listAttributeValue;
    }

    public static void waitForPageLoad(int timeout) {
        WebDriver driver = instance().getWebDriver();
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
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

    public static void backToPreviousURL(String previousURL, int waitTime) {
        String newURL;
        boolean check = false;
        for (int i = 1; i < 5; i++) {
            if(check)
                break;
            else {
                instance().getWebDriver().navigate().back();
                waitForPageLoad(waitTime);
                newURL = instance().getWebDriver().getCurrentUrl();
                check = previousURL.equals(newURL);
            }
        }
    }

    public static void closeAndSwitchToWindowHandle(String windowsHandle) {
        Set<String> setWindowHandles = WebDriverFactory.instance().getWebDriver().getWindowHandles();
        for (String w: setWindowHandles) {
            if(!w.equals(windowsHandle)) {
                WebDriverFactory.instance().getWebDriver().switchTo().window(w);
                WebDriverFactory.instance().getWebDriver().close();
            }
        }
        WebDriverFactory.instance().getWebDriver().switchTo().window(windowsHandle);
    }

    public static void switchToAnotherWindow() {
        Set<String> setWindowHandles = WebDriverFactory.instance().getWebDriver().getWindowHandles();
        String currentWindow = WebDriverFactory.instance().getWebDriver().getWindowHandle();
        for (String w: setWindowHandles) {
            if(!w.equals(currentWindow)) {
                WebDriverFactory.instance().getWebDriver().switchTo().window(w);
            }
        }
    }
}