package UI.Login;

import base.BaseTest;
import com.qtr.core.dataprovider.ExcelDataProvider;
import component.TopHeader;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Login_VerifyValid extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private TopHeader header;

    private void setup() {
        //Setup test data
        ExcelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Initiate page classes
        homePage = new HomePage();
        loginPage = new LoginPage();
        header = new TopHeader();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test
    public void loginWithValidData() {
        //Setup test data
        this.setup();

        //Get data in excel
        String validPhone = ExcelDataProvider.getCellData(3, 0);
        String validPassword = ExcelDataProvider.getCellData(3, 1);

        //Enter valid data
        loginPage.enterPhonedAndPassword(validPhone,validPassword);
        loginPage.clickSubmit();

        //Verify Profile displays
        header.verifyClickProfileLinkWhenLogin();
    }
}
