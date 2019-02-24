package UI.Home;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class Home_VerifyCommonKeyword extends BaseTest {

    private HomePage homePage;

    private void setup() {
        //Go to Home page
        homePage = new HomePage();
        homePage.openHomePage();
    }

    @Test
    public void verifyClickOnCommonKeywords() {
        // Run setup
        this.setup();

        // Verify actions on common keywords section
        homePage.verifyUserCanSelectCommonKeyword();
    }
}
