package com.qtr.core.keyword.assertion;

import org.testng.asserts.SoftAssert;

public class TestNGSoftAssert {

    private static TestNGSoftAssert testNGSoftAssert;
    private static SoftAssert softAssert;

    private TestNGSoftAssert() {
        softAssert = new SoftAssert();
    }

    public static synchronized TestNGSoftAssert instance() {
        if(testNGSoftAssert == null)
            testNGSoftAssert = new TestNGSoftAssert();
        return testNGSoftAssert;
    }

    public void assertAll() {
        softAssert.assertAll();
    }

    public void assertEquals(Object object1, Object object2) {
        softAssert.assertEquals(object1,object2);
    }

    public void assertNotEquals(Object object1, Object object2) {
        softAssert.assertNotEquals(object1,object2);
    }

    public void assertTrue(Boolean object) {
        softAssert.assertTrue(object);
    }

    public void assertFalse(Boolean object) {
        softAssert.assertFalse(object);
    }

    public void fail(String message) {
        softAssert.fail(message);
    }
}
