package com.orange.qa.listeners;

import com.orange.qa.utlis.TestUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {

        TestUtil.getScreenShot(result.getMethod().getMethodName());
        TestUtil.sendMail();
    }
}
