package UI.Debug;

import base.BaseTest;
import com.qtr.core.exception.FailureHandling;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qtr.core.exception.ExceptionHandler.exceptionHandler;

public class debug2 extends BaseTest {

    @Test()
    public void test2(){
        try {
            Assert.assertFalse(true);
        } catch (AssertionError err) {
            exceptionHandler(err, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }
}

