package component;

import com.qtr.core.base.BasePage;
import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

public class TopHeader extends BasePage {
    private int minWaitTime = 1;
    private int maxWaitTime = 3;

    public TopHeader() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(xpath = "//*[@alt=\"Chợ Tốt\"]")
    private WebElement img_Logo;

    @FindBy(xpath = "//*[text()=\"Tìm rao vặt\"]")
    private WebElement lnk_SearchAds;

    @FindBy(xpath = "//*[@id=\"btnNotification\"]//*[text()=\"Thông báo\"]")
    private WebElement lnk_Notification;

    @FindBy(xpath = "//*[@aria-label=\"notification\"]//*[text()=\"HOẠT ĐỘNG\"]")
    private WebElement lnk_Activities;

    @FindBy(xpath = "//*[@aria-label=\"notification\"]//*[text()=\"TIN MỚI\"]")
    private WebElement lnk_News;

    @FindBy(xpath = "//a[text()=\"Đăng ký / Đăng nhập\"]")
    private WebElement btn_RegisterOrLogin;

    @FindBy(xpath = "//*[text()=\"Tôi bán\"]")
    private WebElement lnk_IBuy;

    @FindBy(xpath = "//*[text()=\"Đăng Tin\"]")
    private WebElement lnk_PostNews;

    @FindBy(xpath = "//*[text()=\"Đăng nhập / Đăng ký\"]")
    private WebElement lnk_LoginOrRegister;

    @FindBy(xpath = "//*[text()=\"Tin đăng đã lưu\"]")
    private WebElement lnk_SavedPost;

    @FindBy(xpath = "//*[text()=\"Tìm kiếm đã lưu\"]")
    private WebElement lnk_SavedSearch;

    @FindBy(xpath = "//*[text()=\"Tạo Cửa hàng/Chuyên trang\"]")
    private WebElement lnk_CreateStore;

    @FindBy(xpath = "//*[text()=\"Quảng cáo Tốt\"]")
    private WebElement lnk_BestAds;

    @FindBy(xpath = "//*[text()=\"Vòng quay may quá\"]")
    private WebElement lnk_LuckySpinner;

    @FindBy(xpath = "//*[text()=\"Tải ứng dụng miễn phí\"]")
    private WebElement lnk_FreeDownloadApp;

    @FindBy(xpath = "//*[@class=\"appWrapper-Header-menuRightItem\" and text()=\"Trợ giúp\"]")
    private WebElement lnk_Help;

    @FindBy(xpath = "//*[text()=\"Tài khoản Đồng Tốt\"]")
    private WebElement lnk_GoodVNDAccount;

    @FindBy(xpath = "//*[text()=\"Lịch sử giao dịch\"]")
    private WebElement lnk_TransactionHistory;

    @FindBy(xpath = "//*[text()=\"Cài đặt thông tin\"]")
    private WebElement lnk_SetupInfo;

    @FindBy(xpath = "//a[@id=\"btnMenuMore\"]")
    private WebElement btn_MenuMore;

    @FindBy(xpath = "//*[text()=\"Đăng xuất\"]")
    private WebElement lnk_Logout;

    @FindBy(xpath = "//a[text()=\"Xem trang cá nhân của bạn\"]")
    private WebElement lnk_GoToProfile;

    public void clickLogout() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_Logout);
    }

    public void verifyUserCanSelectItemOnTopHeaderWithoutLogin() {
        waitForElementStaleness(img_Logo, minWaitTime);
        waitForElementVisible(img_Logo, minWaitTime);
        String currentURL = WebDriverFactory.instance().getWebDriver().getCurrentUrl();

        this.verifyClickSearchAdsWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickNotificationWithoutLogin();

        this.verifyClickIBuyWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickPostNewsWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickLoginOrRegisterWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickSavedPostWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickSavedSearchWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickCreateStoreWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickBestAdsWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickLuckySpinnerWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);

//        this.verifyClickFreeDownloadWithoutLogin();
//        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickHelpWithoutLogin();
        backToPreviousURL(currentURL, maxWaitTime);
    }

    public void verifyUserCanSelectItemOnTopHeaderWhenLogin() {
        waitForElementStaleness(img_Logo, minWaitTime);
        waitForElementVisible(img_Logo, minWaitTime);
        String currentURL = WebDriverFactory.instance().getWebDriver().getCurrentUrl();

        this.verifyClickSearchAdsWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickNotificationWhenLogin();

        this.verifyClickIBuyWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickPostNewsWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickProfileLinkWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickSavedPostWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickSavedSearchWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickGoodVndAccountWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickTransactionHistoryWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickCreateStoreWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickBestAdsWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickLuckySpinnerWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickFreeDownloadWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickHelpWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickSetupInfoWhenLogin();
        backToPreviousURL(currentURL, maxWaitTime);

        this.verifyClickLogoutWhenLogin();
    }

    // ***************** For Logging ************************************

    public void verifyClickSearchAdsWhenLogin() {
        moveAndClick(lnk_SearchAds);
        verifyPageTitleContainsText("Rao Vặt", maxWaitTime);
    }

    public void verifyClickNotificationWhenLogin() {
        moveAndClick(lnk_Notification);
        verifyElementVisible(lnk_Activities, maxWaitTime);
        verifyElementVisible(lnk_News, maxWaitTime);
    }

    public void verifyClickIBuyWhenLogin() {
        moveAndClick(lnk_IBuy);
        verifyPageTitleContainsText("Quản lý tin", maxWaitTime);
    }

    public void verifyClickPostNewsWhenLogin() {
        moveAndClick(lnk_PostNews);
        verifyPageTitleContainsText("Đăng tin", maxWaitTime);
    }

    public void verifyClickProfileLinkWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_GoToProfile);
        verifyPageTitleContainsText("Trang cá nhân", maxWaitTime);
    }

    public void verifyClickSavedPostWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_SavedPost);
        verifyPageTitleContainsText("Tin đăng đã lưu", maxWaitTime);
    }

    public void verifyClickSavedSearchWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_SavedSearch);
        verifyPageTitleContainsText("Tìm kiếm đã lưu", maxWaitTime);
    }

    public void verifyClickGoodVndAccountWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_GoodVNDAccount);
        verifyPageTitleContainsText("Tài khoản Đồng Tốt", maxWaitTime);
    }

    public void verifyClickTransactionHistoryWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_TransactionHistory);
        verifyPageURLContainsText("history", maxWaitTime);
    }

    public void verifyClickCreateStoreWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_CreateStore);
        verifyPageURLContainsText("Shopcreate", maxWaitTime);
    }

    public void verifyClickBestAdsWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_BestAds);
        verifyPageTitleContainsText("Quản lý quảng cáo", maxWaitTime);
    }

    public void verifyClickLuckySpinnerWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_LuckySpinner);
        verifyPageTitleContainsText("Vòng Quay Lễ Hội", maxWaitTime);
    }

    public void verifyClickFreeDownloadWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_FreeDownloadApp);
        verifyPageTitleContainsText("ứng dụng Chợ Tốt", maxWaitTime);
    }

    public void verifyClickHelpWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_Help);
        verifyPageTitleContainsText("Trợ giúp", maxWaitTime);
    }

    public void verifyClickSetupInfoWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_SetupInfo);
        verifyPageTitleContainsText("Trang cá nhân", maxWaitTime);
    }

    public void verifyClickLogoutWhenLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_Logout);
        new HomePage().verifyLoginNowDisplayed();
    }

    // ***************** For no Logging ************************************

    public void verifyClickSearchAdsWithoutLogin() {
        moveAndClick(lnk_SearchAds);
        verifyPageTitleContainsText("Rao Vặt", maxWaitTime);
    }

    public void verifyClickNotificationWithoutLogin() {
        moveAndClick(lnk_Notification);
        verifyElementVisible(lnk_Activities, maxWaitTime);
        verifyElementVisible(lnk_News, maxWaitTime);
        verifyElementVisible(btn_RegisterOrLogin, maxWaitTime);
    }

    public void verifyClickIBuyWithoutLogin() {
        moveAndClick(lnk_IBuy);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickPostNewsWithoutLogin() {
        moveAndClick(lnk_PostNews);
        verifyPageTitleContainsText("Đăng tin", maxWaitTime);
    }

    public void verifyClickLoginOrRegisterWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_LoginOrRegister);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickSavedPostWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_SavedPost);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickSavedSearchWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_SavedSearch);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickCreateStoreWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_CreateStore);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickBestAdsWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_BestAds);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickLuckySpinnerWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_LuckySpinner);
        verifyPageTitleContainsText("Vòng Quay Lễ Hội", maxWaitTime);
    }

    public void verifyClickFreeDownloadWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_FreeDownloadApp);
        verifyPageTitleContainsText("ứng dụng Chợ Tốt", maxWaitTime);
    }

    public void verifyClickHelpWithoutLogin() {
        moveAndClick(btn_MenuMore);
        moveAndClick(lnk_Help);
        verifyPageTitleContainsText("Trợ giúp", maxWaitTime);
    }

}