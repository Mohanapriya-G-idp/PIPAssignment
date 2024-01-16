package com.demo.qa.listeners;

import com.demo.qa.base.TestBase;
import com.demo.qa.utils.TestUtil;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CustomListener extends TestBase implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        TestUtil.getScreenShot(result.getMethod().getMethodName());
        TestUtil.sendMail();
    }

}
