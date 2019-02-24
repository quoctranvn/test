package pages;

import com.qtr.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qtr.core.keyword.selenium.ElementKeyword.verifyElementPresent;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"ĐĂNG KÝ\"]")
    private WebElement lbl_Register;

    public void verifyRegisterPageDisplay() {
        verifyElementPresent(lbl_Register);
    }
}
