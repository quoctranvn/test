package UI.Login;

import base.BaseTest;
import component.TopHeader;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Login_VerifyValid extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private TopHeader topHeader;

    private void setup() {
        //Setup test data
        excelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Initiate page instances
        loginPage = new LoginPage();
        homePage = new HomePage();
        topHeader = new TopHeader();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test
    public void loginWithValidData() {
        //Setup test data
        this.setup();

        //Get data in excel
        String validPhone = excelDataProvider.getCellData(3, 0);
        String validPassword = excelDataProvider.getCellData(3, 1);

        //Enter valid data
        loginPage.enterPhonedAndPassword(validPhone,validPassword);
        loginPage.clickSubmit();

        //Verify Profile displays
        topHeader.verifyClickProfileLinkWhenLogin();
    }
}
