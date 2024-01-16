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
        framesAlertPage = new FramesAlertPage();
        framesAlertPage.goToFramesPage();
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

}
