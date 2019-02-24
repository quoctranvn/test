package UI.Login;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Login_VerifyInvalidWithDataProvider extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @DataProvider(name = "invalidLogin")
    public Object[][] createData() {
        return excelDataProvider.getTableArray("TestData.xlsx", "Login", 1);
    }

    @BeforeMethod( firstTimeOnly = true)
    private void setup() {
        //Setup test data
        excelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Initiate page instances
        homePage = new HomePage();
        loginPage = new LoginPage();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test(dataProvider = "invalidLogin")
    public void loginWithInvalidData(String invalidPhone, String invalidPassword) {
        // Login with invalid data
        loginPage.enterPhonedAndPassword(invalidPhone,invalidPassword);
        loginPage.clickSubmit();
    }

}
