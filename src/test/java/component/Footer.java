package component;

import com.qtr.core.base.BasePage;
import com.qtr.core.driver.selenium.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static com.qtr.core.keyword.selenium.ActionKeyword.moveAndClick;
import static com.qtr.core.keyword.selenium.PageKeyword.closeAndSwitchToWindowHandle;
import static com.qtr.core.keyword.selenium.PageKeyword.switchToAnotherWindow;

public class Footer extends BasePage {

    @FindBy(xpath = "//*[@class=\"appWrapper-DesktopFooter\"]//a[@class=\"appWrapper-Footer-a\"][not(img)]")
    private List<WebElement> lst_FooterLinkText;

    @FindBy(xpath = "//*[@class=\"appWrapper-DesktopFooter\"]//a[@class=\"appWrapper-Footer-a\"]/img")
    private List<WebElement> lst_FooterLinkImg;

    public void verifyUserCanSelectLinkUnderFooter(){
        this.verifyUserCanSelectLinkText();
        this.verifyUserCanSelectLinkImage();
    }

    public void verifyUserCanSelectLinkImage(){
        String newPageTitile, altText;
        String currentWindowHandle = DriverFactory.instance().getWebDriver().getWindowHandle();
        String currentPageTitle = DriverFactory.instance().getWebDriver().getTitle();
        // Check link image
        for(int i = 0; i < lst_FooterLinkImg.size(); i++) {
            // Get link text
            altText = lst_FooterLinkImg.get(i).getAttribute("alt");
            // Click on footer link
            moveAndClick(lst_FooterLinkImg.get(i));
            // Get new page title, new window handle
            switchToAnotherWindow();
            newPageTitile = DriverFactory.instance().getWebDriver().getTitle();
            // Check other page title displays
            try{
                Assert.assertNotEquals(currentPageTitle, newPageTitile, "\nNew page title \""
                        + newPageTitile + "\" and previous page title \"" + currentPageTitle + "\" are the same");
                Assert.assertEquals(newPageTitile.contains(altText), true, "\nNew page title \""
                        + newPageTitile + "\" does not contains text link \"" + altText + "\"");
            } catch (AssertionError err) {
                System.err.println(err.getMessage());
            }
            // Back to previous window
            closeAndSwitchToWindowHandle(currentWindowHandle);
        }
    }

    public void verifyUserCanSelectLinkText(){
        String newPageTitile, linkText;
        String currentWindowHandle = DriverFactory.instance().getWebDriver().getWindowHandle();
        String currentPageTitle = DriverFactory.instance().getWebDriver().getTitle();
        // Check link text
        for(int i = 0; i < lst_FooterLinkText.size(); i++) {
            // Get link text
            linkText = lst_FooterLinkText.get(i).getText();
            // Click on footer link
            moveAndClick(lst_FooterLinkText.get(i));
            // Get new page title, new window handle
            switchToAnotherWindow();
            newPageTitile = DriverFactory.instance().getWebDriver().getTitle();
            // Check other page title displays
            try{
                Assert.assertNotEquals(currentPageTitle, newPageTitile, "\nNew page title \""
                        + newPageTitile + "\" and previous page title \"" + currentPageTitle + "\" are the same");
                Assert.assertEquals(newPageTitile.contains(linkText), true, "\nNew page title \""
                        + newPageTitile + "\" does not contains text link \"" + linkText + "\"");
            } catch (AssertionError err) {
                System.err.println(err.getMessage());
            }
            // Back to previous window
            closeAndSwitchToWindowHandle(currentWindowHandle);
        }
    }
}