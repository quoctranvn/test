package component;

import com.qtr.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qtr.core.configuration.WaitTime.getMaxWaitTime;
import static com.qtr.core.configuration.WaitTime.getMinWaitTime;
import static com.qtr.core.keyword.selenium.WaitKeyword.waitForElementVisible;

public class Popup extends BasePage {
    private int minWaitTime = getMinWaitTime();
    private int maxWaitTime = getMaxWaitTime();

    @FindBy(xpath = "//*[@class=\"ab-image-area\"]")
    private WebElement img_PopupAds;

    @FindBy(xpath = "//*[@aria-label=\"Close Message\"]")
    private WebElement img_ClosePopup;

    public void closePopup() {
        if(waitForElementVisible(img_ClosePopup,  minWaitTime)) {
            img_ClosePopup.click();
        }
    }
}
