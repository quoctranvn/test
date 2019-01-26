package pages;

import com.qtr.core.base.BasePage;
import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"ĐĂNG KÝ\"]")
    private WebElement lbl_Register;

    public void verifyRegisterPageDisplay() {
        verifyElementPresent(lbl_Register);
    }
}
