package component;

import com.qtr.core.base.BasePage;
import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Popup extends BasePage {

    private int minWaitTime = 1;
    private int maxWaitTime = 3;

    public Popup() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(xpath = "//*[@class=\"ab-image-area\"]")
    private WebElement img_PopupAds;

    @FindBy(xpath = "//*[@aria-label=\"Close Message\"]")
    private WebElement img_ClosePopup;

    public void closePopup() {
        if(waitForElementVisible(img_ClosePopup, maxWaitTime)) {
            img_ClosePopup.click();
            waitForPageLoad(minWaitTime);
        }
    }
}
