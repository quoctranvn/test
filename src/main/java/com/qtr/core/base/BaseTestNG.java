package com.qtr.core.base;

import com.qtr.core.listener.TestNG.SuiteListener;
import com.qtr.core.listener.TestNG.TestListener;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, SuiteListener.class})
public class BaseTestNG {

}
