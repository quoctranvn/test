package UI.Home;

import base.BaseTest;
import component.TopHeader;
import org.testng.annotations.Test;
import pages.HomePage;

public class Home_VerifyTopHeaderWithoutLogin extends BaseTest {
    private HomePage homePage;
    private TopHeader topHeader;

    private void setup() {
        //Initiate page classes
        homePage = new HomePage();
        topHeader = new TopHeader();

        //Go to Login page
        homePage.openHomePage();
    }

    @Test
    public void checkTopHeaderWhenLogin() {
        // Run setup
        this.setup();

        // Verify actions on top header section
        topHeader.verifyUserCanSelectItemOnTopHeaderWithoutLogin();

    }
}
