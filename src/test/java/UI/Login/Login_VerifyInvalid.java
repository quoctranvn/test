package UI.Login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Login_VerifyInvalid extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    private void setup() {
        //Setup test data
        excelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Initiate page classes
        homePage = new HomePage();
        loginPage = new LoginPage();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test()
    public void loginWithBlankData() {
        // Run setup
        this.setup();

        // Login with blank data
        loginPage.clickSubmit();
        loginPage.verifyRequiredFields();
    }

    @Test(priority = 1)
    public void loginWithInvalidData() {
        //Get data in excel
        String invalidPhone = excelDataProvider.getCellData(1, 0);
        String invalidPassword = excelDataProvider.getCellData(1, 1);

        // Login with invalid data
        loginPage.enterPhonedAndPassword(invalidPhone,invalidPassword);
        loginPage.clickSubmit();
        loginPage.checkInvalidData();
    }

    @Test(priority = 2)
    public void loginWithWrongData() {
        //Get data in excel
        String wrongPhone = excelDataProvider.getCellData(2, 0);
        String wrongPassword = excelDataProvider.getCellData(2, 1);

        // Login with wrong data
        loginPage.enterPhonedAndPassword(wrongPhone, wrongPassword);
        loginPage.clickSubmit();
        loginPage.checkWrongData();
    }
}
