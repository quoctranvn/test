package com.qtr.core.keyword.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.qtr.core.driver.selenium.DriverFactory.instance;

public class ElementKeyword {

    public static void verifyElementPresent(WebElement webElement) {
        Assert.assertEquals(webElement.isDisplayed(), true,
                "\nWeb element " + webElement  + " is not displayed");
    }

    public static void verifyElementVisible(WebElement webElement, int waitTime){
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void verifyElementNotVisible(WebElement webElement, int waitTime){
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void verifyTextPresent(String textValue, Boolean isExactly, int waitTime) {
        String xpath;
        if(isExactly)
            xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }
}
