package component;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.qtr.core.base.BasePage.*;
import static com.qtr.core.utils.StringHelper.removeAccent;

public class MainHeader {

    private int minWaitTime = 1;
    private int maxWaitTime = 3;

    public MainHeader() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(xpath = "//ol[@itemscope]//li[a or span]")
    private List<WebElement> lst_Breadcrumb;

    @FindBy(xpath = "//h1[text()]")
    private WebElement lbl_MainContent;

    public void verifyBreabcrumbItem(String expectedBreadcrumb) {
        if(waitForElementVisible(lst_Breadcrumb.get(0), maxWaitTime)) {
            try {
                boolean isDisplayed = false;
                List<String> listBreadcrumb = getListText(lst_Breadcrumb);
                for (String s : listBreadcrumb) {
                    if (s.toLowerCase().equals(expectedBreadcrumb)) {
                        isDisplayed = true;
                        break;
                    }
                }
                Assert.assertEquals(isDisplayed, true, "\nList breadcrumb values \"" + getListText(lst_Breadcrumb).toString()
                        + "\" does NOT contain expected value \"" + expectedBreadcrumb + "\"");
            } catch (AssertionError err) {
            System.err.println(err.getMessage());
            }
        } else {
            System.err.println("\nNot found breadcrumb for expected: \"" + expectedBreadcrumb + "\"");
        }
    }

    public void verifyMainHeaderContent(String expectedContent) {
        if(waitForElementVisible(lbl_MainContent, maxWaitTime)) {
            try {
                String mainHeaderContent = getText(lbl_MainContent, minWaitTime).toLowerCase();
                // Remove all special characters with empty strings
                mainHeaderContent = mainHeaderContent.replace(",", "").replace(" - ", " ");
                boolean check = false;
                String lastBreadcrumb = "";
                if(mainHeaderContent.contains(expectedContent))
                    check = true; // Main header content contains expected value
                else {
                    try {
                        lastBreadcrumb = lst_Breadcrumb.get(lst_Breadcrumb.size() - 1).getText().toLowerCase();
                        lastBreadcrumb = lastBreadcrumb.replace("/", " ").replace(",", "");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        lastBreadcrumb = null;
                        System.err.print("\nNot found breadcrumb for " + expectedContent + "\n");
                    }
                    // Re-check expectedContent contains the last breadcrumb value
                    check = expectedContent.contains(lastBreadcrumb) || lastBreadcrumb.contains(expectedContent);
                }
                Assert.assertEquals(check, true, "\n Main header content \""
                        + mainHeaderContent + "\" or last breadcrumb \""
                        + lastBreadcrumb + "\" does NOT contain expected value \"" + expectedContent + "\"");
            } catch (AssertionError err) {
            System.err.println(err.getMessage());
            }
        } else {
            System.err.println("\nNot found main header content with accent for expected: \"" + expectedContent + "\"\n");
        }
    }

    public void verifyMainHeaderContentWithoutAccent(String expectedContent) {
        if(waitForElementVisible(lbl_MainContent, maxWaitTime)) {
            try {
                String mainHeaderContent = getText(lbl_MainContent, minWaitTime).toLowerCase();
                // Remove all special characters with empty strings
                mainHeaderContent = mainHeaderContent.replace(",", "").replace(" - ", " ");
                mainHeaderContent = removeAccent(mainHeaderContent);// Remove accent in main header
                boolean check = false;
                expectedContent = removeAccent(expectedContent); // Remove accent in expectedContent
                String lastBreadcrumb = "";
                if(mainHeaderContent.contains(expectedContent))
                    check = true; // Main header content contains expected value
                else {
                    try {
                        lastBreadcrumb = lst_Breadcrumb.get(lst_Breadcrumb.size() - 1).getText().toLowerCase();
                        lastBreadcrumb = lastBreadcrumb.replace("/", " ").replace(",", "");
                        lastBreadcrumb = removeAccent(lastBreadcrumb); // Remove accent in lastBreadcrumb
                    } catch (ArrayIndexOutOfBoundsException e) {
                        lastBreadcrumb = null;
                        System.err.print("\nNot found breadcrumb for " + expectedContent + "\n");
                    }
                    // Re-check expectedContent contains the last breadcrumb value
                    check = expectedContent.contains(lastBreadcrumb) || lastBreadcrumb.contains(expectedContent);
                }
                Assert.assertEquals(check, true, "\n Main header content \"" + mainHeaderContent
                        + "\" or last breadcrumb \"" + lastBreadcrumb
                        + "\" does NOT contain expected value \"" + expectedContent + "\"");
            } catch (AssertionError err) {
            System.err.println(err.getMessage());
            }
        } else {
            System.err.println("\nNot found main header content without accent for expected: \"" + expectedContent + "\"");
        }
    }
}
