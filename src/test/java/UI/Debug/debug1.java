package UI.Debug;

import base.BaseTest;
import com.qtr.core.exception.FailureHandling;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qtr.core.exception.ExceptionHandler.exceptionHandler;

public class debug1 extends BaseTest {

    @Test()
    public void test1(){
        try {
            Assert.assertTrue(false);
        } catch (AssertionError err) {
            exceptionHandler(err, FailureHandling.WARNING);
        }
    }
}

