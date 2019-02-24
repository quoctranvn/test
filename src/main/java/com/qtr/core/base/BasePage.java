package com.qtr.core.base;

import com.qtr.core.driver.selenium.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        initPageObject();
    }

    private void initPageObject() {
        PageFactory.initElements(DriverFactory.instance().getWebDriver(), this);
    }
}
