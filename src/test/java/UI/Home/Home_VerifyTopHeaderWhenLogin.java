package UI.Home;

import base.BaseTest;
import component.Footer;
import component.TopHeader;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Home_VerifyTopHeaderWhenLogin extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private TopHeader topHeader;
    private Footer footer;

    private void setup() {
        //Setup test data
        excelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Get data in excel
        String validPhone = excelDataProvider.getCellData(3, 0);
        String validPassword = excelDataProvider.getCellData(3, 1);

        //Initiate page instances
        loginPage = new LoginPage();
        homePage = new HomePage();
        topHeader = new TopHeader();
        footer = new Footer();

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
