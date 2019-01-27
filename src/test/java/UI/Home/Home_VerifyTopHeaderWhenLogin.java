package UI.Home;

import base.BaseTest;
import com.qtr.core.dataprovider.ExcelDataProvider;
import component.TopHeader;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Home_VerifyTopHeaderWhenLogin extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private TopHeader topHeader;

    private void setup() {
        //Setup test data
        ExcelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Get data in excel
        String validPhone = ExcelDataProvider.getCellData(3, 0);
        String validPassword = ExcelDataProvider.getCellData(3, 1);

        //Initiate page classes
        homePage = new HomePage();
        loginPage = new LoginPage();
        topHeader = new TopHeader();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();

        //Enter valid data
        loginPage.enterPhonedAndPassword(validPhone,validPassword);
        loginPage.clickSubmit();
    }

    @Test
    public void verifyTopHeaderWhenLogin() {
        // Run setup
        this.setup();

        // Verify actions on top header section
        topHeader.verifyUserCanSelectItemOnTopHeaderWhenLogin();

    }
}
