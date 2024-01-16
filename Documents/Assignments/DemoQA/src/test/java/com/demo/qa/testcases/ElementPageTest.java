package com.demo.qa.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demo.qa.base.TestBase;
import com.demo.qa.constants.Constants;
import com.demo.qa.pages.ElementPage;
import com.demo.qa.pages.HomePage;
import com.demo.qa.utils.TestUtil;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class ElementPageTest extends TestBase {
    HomePage homePage;
    ElementPage elementPage;
    String input;


    public ElementPageTest() {
        super();
        extentReports = new ExtentReports();
        sp = new ExtentSparkReporter("");
        extentReports.attachReporter(sp);
    }

    @BeforeClass
    public void visitURL() {
        launchBrowser();
        homePage = new HomePage();
        homePage.clickOnElementIcon();
        elementPage = new ElementPage();
    }

    @BeforeMethod
    public void setUpReports(ITestResult result) {
        methodName = result.getMethod().getMethodName();
        test = extentReports.createTest(methodName).assignAuthor(author).assignCategory(category);
    }

    @Test
    public void verifyElementPageTest() {
        elementPage.validateElementPage();
    }

    @Test(priority = 1)
    public void verifyTextBoxPageTest() {
        elementPage.clickOnTextBox();
        elementPage.validateTextPage();
    }

    @DataProvider
    public Object[][] getDataFromCSV() {
        Object[][] csvData = TestUtil.getData().toArray(new String[0][]);
        return csvData;
    }

    @Test(priority = 2, dataProvider = "getDataFromCSV")
    public void completeFormWithCredentials(String name, String mail, String address) {
        elementPage.enterCredentialsInForm(name, mail, address);
        input = name;
    }

    @Test(priority = 3)
    public void verifyResult() {
        String output = elementPage.validateTextEntered();
        Assert.assertEquals(input, output);
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



