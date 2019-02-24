package base;

import com.qtr.core.base.BaseTestNG;
import com.qtr.core.data.ExcelProvider;
import com.qtr.core.driver.selenium.DriverFactory;
import org.testng.annotations.*;

public class BaseTest extends BaseTestNG {
    protected ExcelProvider excelDataProvider = new ExcelProvider();

    @BeforeClass
    @Parameters("browserName")
    public void setUpTestClass(@Optional("chrome")String browserName) {
        DriverFactory.instance().createWebDriver(browserName);
    }

    @AfterClass
    public void tearDownTestClass() {
        DriverFactory.instance().disposeWebDriver();
    }

}
