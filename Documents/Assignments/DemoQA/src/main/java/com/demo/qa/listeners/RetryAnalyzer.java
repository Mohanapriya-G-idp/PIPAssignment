package com.demo.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int maxCount =2;
    private int retryCount =0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount<maxCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
