package pages;

import com.qtr.core.base.BasePage;
import component.MainHeader;
import component.Popup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.qtr.core.configuration.WaitTime.getMaxWaitTime;
import static com.qtr.core.configuration.WaitTime.getMinWaitTime;
import static com.qtr.core.keyword.selenium.ActionKeyword.*;
import static com.qtr.core.keyword.selenium.ElementKeyword.verifyElementVisible;
import static com.qtr.core.keyword.selenium.PageKeyword.*;
import static com.qtr.core.keyword.selenium.WaitKeyword.waitForElementPresent;

public class HomePage extends BasePage {

    private int minWaitTime = getMinWaitTime();
    private int maxWaitTime = getMaxWaitTime();

    private final String homePageURL = "https://www.chotot.com/";

    @FindBy(xpath = "//*[text()=\"Đăng nhập ngay\"]")
    private WebElement btn_LoginNow;

    @FindBy(xpath = "//*[@title=\"Đăng ký tài khoản\"]")
    private WebElement btn_Register;

    @FindBy(xpath = "//*[contains(@class, \"HomePage__WrapperBanner\")]//a[@itemprop=\"url\"]")
    private List<WebElement> lst_BannerItem;

    @FindBy(xpath = "//*[@id=\"boxListCate\"]//a")
    private List<WebElement> lst_Category;

    @FindBy(xpath = "//p[@id=\"contentSeoCat\"]//p/a")
    private List<WebElement> lst_HyperlinkText;

    @FindBy(xpath = "//li[@itemscope]/a")
    private List<WebElement> lst_CommonKeyword;

    @FindBy(xpath = "//a[@id=\"buttonSeeMore\" and text()=\"Xem thêm\"]")
    private WebElement btn_SeeMore;

    public void openHomePage() {
        openURL(homePageURL);
    }

    public void clickLoginNow() {
        new Popup().closePopup();
        moveAndClickJS(btn_LoginNow);
    }

    public void verifyLoginNowDisplayed() {verifyElementVisible(btn_LoginNow, maxWaitTime * 3);}

    public void clickRegister() { moveAndClick(btn_Register); }

    public void verifyUserCanClickRegister() {
     this.clickRegister();
     verifyPageTitleContainsText("ĐĂNG KÝ", maxWaitTime);
    }

    public void verifyUserCanSelectBannerItem() {
        String hrefBannerItem, bannerContent;
        for(int i = 0; i < lst_BannerItem.size(); i++) {
            // Get href of banner item
            hrefBannerItem = lst_BannerItem.get(i).getAttribute("href");
            if(hrefBannerItem.contains("?"))
                bannerContent = hrefBannerItem.substring(hrefBannerItem.lastIndexOf("/") + 1, hrefBannerItem.lastIndexOf("?")).replace("-", " ").toLowerCase();
            else bannerContent = hrefBannerItem.substring(hrefBannerItem.lastIndexOf("/") + 1).replace("-", " ").toLowerCase();
            // Click on banner item
            moveAndClickJS(lst_BannerItem.get(i));
            // Verify banner item displayed on header list
            new MainHeader().verifyMainHeaderContentWithoutAccent(bannerContent);
            // Back to Home page
            backToPreviousURL(this.homePageURL, maxWaitTime);
        }
    }

    public void verifyUserCanSelectCategory() {
        waitForElementPresent(lst_Category.get(0), maxWaitTime);
        String categoryName;
        for(int i = 0; i < lst_Category.size(); i++) {
            // Get category name
            categoryName = getText(lst_Category.get(i), minWaitTime).toLowerCase();
            // Click on category name
            moveAndClick(lst_Category.get(i));
            // Verify category name displayed on breadcrumb list
            new MainHeader().verifyBreabcrumbItem(categoryName);
            // Back to Home page
            backToPreviousURL(this.homePageURL, maxWaitTime);
        }
    }

    public void verifyUserCanSelectCommonKeyword() {
        String selectedKeyword;
        // Verify selected keyword display after clicking
        for (int i = 0; i < lst_CommonKeyword.size(); i++) {
            // Get keyword
            selectedKeyword = getText(lst_CommonKeyword.get(i), minWaitTime).toLowerCase();
            selectedKeyword = selectedKeyword.replace(",", "");
            // Click keyword
            moveAndClickJS(lst_CommonKeyword.get(i));
            // Verify selected keyword displayed on header or breadcrumb
            new MainHeader().verifyMainHeaderContent(selectedKeyword);
            // Back to Home page
            backToPreviousURL(this.homePageURL, maxWaitTime);
        }
    }

    public void verifyUserCanSelectHyperlinkText() {
        String textValue;
        // Verify selected text display after clicking
        for(int i = 0; i < lst_HyperlinkText.size()-1; i++){
            // Get hyperlink value
            textValue = getText(lst_HyperlinkText.get(i), minWaitTime).toLowerCase();
            textValue = textValue.replace(",", "");
            // Click on text
            moveAndClick(btn_SeeMore);
            moveAndClickJS(lst_HyperlinkText.get(i));
            // Verify selected text displayed on header or breadcrumb
            new MainHeader().verifyMainHeaderContent(textValue);
            // Back to Home page
            backToPreviousURL(this.homePageURL, maxWaitTime);
        }
    }
}
