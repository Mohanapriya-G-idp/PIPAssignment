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
        homePage = new HomePage();
        homePage.clickOnElementIcon();
        elementPage = new ElementPage();
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

}



