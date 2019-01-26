package UI.Home;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class Home_VerifyCommonKeyword extends BaseTest {
    private HomePage homePage;

    private void setup() {
        //Initiate page classes
        homePage = new HomePage();

        //Go to Home page
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
