package com.orange.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orange.qa.constants.Constants;
import com.orange.qa.utlis.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties properties;
    public static WebDriverWait wait;
    public static String inputName;
    public static ExtentReports extentReports;
    public static ExtentTest test;
    public static ExtentSparkReporter sp;
    public static String author;
    public static String category;
    public static String methodName;
    public static String failedLog;

    public TestBase() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(Constants.PROP_FILE_PATH);
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite
    public void launchBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName.equals(Constants.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals(Constants.EDGE)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_OUT_TIME));

    }

    @BeforeTest
    public void setUpReports() {
        extentReports = new ExtentReports();
        sp = new ExtentSparkReporter(Constants.REPORT_NAME);
        extentReports.attachReporter(sp);
        author = properties.getProperty("auth");
        category = properties.getProperty("cate");
    }

    @BeforeMethod
    public void setUpReportLog(ITestResult result) {
        methodName = result.getMethod().getMethodName();
        test = extentReports.createTest(methodName).assignAuthor(author).assignCategory(category);
    }

    @AfterMethod
    public void statusLogOfReorts(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed: " + methodName);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            failedLog = TestUtil.getScreenShot(methodName);
            test.log(Status.FAIL, "Test Failed: " + methodName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + methodName);
        }
        extentReports.flush();
    }

    @AfterSuite
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
