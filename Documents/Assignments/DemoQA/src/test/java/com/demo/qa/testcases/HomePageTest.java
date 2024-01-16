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
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class HomePageTest extends TestBase {
    HomePage homePage;
    ElementPage elementPage;

    public HomePageTest() {
        super();
        homePage = new HomePage();

    }


    @Test(priority = 1)
    public void verifyHomePageTest() {
        homePage.validateHomePage();
    }

    @Test(priority = 2)
    public void clickOnElementPageTest() {
        elementPage = homePage.clickOnElementIcon();
    }


}
