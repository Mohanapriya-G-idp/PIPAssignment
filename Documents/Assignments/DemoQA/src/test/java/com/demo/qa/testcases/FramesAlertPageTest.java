package com.demo.qa.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demo.qa.base.TestBase;
import com.demo.qa.pages.ElementPage;
import com.demo.qa.pages.FramesAlertPage;
import com.demo.qa.pages.HomePage;
import com.demo.qa.utils.TestUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class FramesAlertPageTest extends TestBase {
    FramesAlertPage framesAlertPage;

    public FramesAlertPageTest() {
        super();
        extentReports = new ExtentReports();
        sp = new ExtentSparkReporter("");
        extentReports.attachReporter(sp);
    }

    @BeforeClass
    public void visitURL() {
        launchBrowser();
        framesAlertPage = new FramesAlertPage();
        framesAlertPage.goToFramesPage();
    }

    @BeforeMethod
    public void setUpReports(ITestResult result) {
        methodName = result.getMethod().getMethodName();
        test = extentReports.createTest(methodName).assignAuthor(author).assignCategory(category);
    }

    @Test
    public void verifyFrameTabTest() {
        framesAlertPage.goToFrameTab();
        framesAlertPage.validateFrames();
    }

    @Test(priority = 1)
    public void verifyWindowsTabTest() {
        framesAlertPage.goToWindowTab();
        framesAlertPage.validateWindows();
    }

    @Test(priority = 2)
    public void verifyAlertsTabTest() {
        framesAlertPage.goToAlertsTab();
        framesAlertPage.validatePromptAlert();
    }

    @AfterMethod
    public void reportStatusLog(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed: " + methodName);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + methodName);
            failLog = TestUtil.getScreenShot(methodName);
            test.addScreenCaptureFromPath(failLog);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + methodName);
        }
    }

    @AfterClass
    public void closeBrowser() {
        extentReports.flush();
        tearDown();
    }
}
