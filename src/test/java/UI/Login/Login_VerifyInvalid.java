package UI.Login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Login_VerifyInvalid extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    private void setup() {
        //Initiate page instances
        homePage = new HomePage();
        loginPage = new LoginPage();

        //Setup test data
        excelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test()
    public void loginWithInvalidData() {
        // Run setup
        this.setup();

        // Get data from excel
        String invalidPhone = excelDataProvider.getCellData(1, 0);
        String invalidPassword = excelDataProvider.getCellData(1, 1);
        String wrongPhone = excelDataProvider.getCellData(2, 0);
        String wrongPassword = excelDataProvider.getCellData(2, 1);

        // Login with blank data
        loginPage.clickSubmit();
        loginPage.verifyRequiredFields();

        // Login with invalid data
        loginPage.enterPhonedAndPassword(invalidPhone,invalidPassword);
        loginPage.clickSubmit();
        loginPage.checkInvalidData();

        // Login with wrong data
        loginPage.enterPhonedAndPassword(wrongPhone, wrongPassword);
        loginPage.clickSubmit();
        loginPage.checkWrongData();
    }
}
