package UI.Home;

import base.BaseTest;
import component.Footer;
import org.testng.annotations.Test;
import pages.HomePage;

public class Home_VerifyFooter extends BaseTest {
    private HomePage homePage;
    private Footer footer;

    private void setup() {
        //Initiate page classes
        homePage = new HomePage();
        footer = new Footer();

        //Go to Home page
        homePage.openHomePage();
    }

    @Test
    public void verifyFooterWhenLogin() {
        // Run setup
        this.setup();

        // Verify actions on footer section
        footer.verifyUserCanSelectLinkUnderFooter();
    }
}
