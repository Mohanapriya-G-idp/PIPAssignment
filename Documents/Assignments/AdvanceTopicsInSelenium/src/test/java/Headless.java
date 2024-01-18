import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Headless extends Base implements ITestListener {

    @Test
    public void google(){
       // driver = new HtmlUnitDriver();
    driver = new HtmlUnitDriver();
        driver.get("https://google.com");
        System.out.println("Title of the page :"+driver.getTitle());
        driver.findElement(By.id("APjFqb")).sendKeys("selenium");
        driver.findElement(By.cssSelector("[aria-label='Google Search']")).click();
        System.out.println("Title of the page :"+driver.getTitle());
        Assert.assertFalse(true);

    }
    @Override
    public void onTestFailure(ITestResult result) {

        failedCases(result.getMethod().getMethodName());
        sendEmail();

    }
//    @AfterClass
//    public void mailSent(ITestResult result) throws EmailException {
//
//        if((result.getStatus()==){
//        sendEmail();
//    }
//
//}
}
