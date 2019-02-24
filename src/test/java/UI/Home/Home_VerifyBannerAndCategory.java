package UI.Home;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class Home_VerifyBannerAndCategory extends BaseTest {

    private HomePage homePage;

    private void setup() {
        //Go to Home page
        homePage = new HomePage();
        homePage.openHomePage();
    }

    @Test
    public void verifyClickOnBannerAndCategory() {
        // Run setup
        this.setup();

        // Verify actions on banner section
        homePage.verifyUserCanSelectBannerItem();

        // Verify actions on category section
        homePage.verifyUserCanSelectCategory();

        // Verify click Register button
        homePage.verifyUserCanClickRegister();
    }
}
