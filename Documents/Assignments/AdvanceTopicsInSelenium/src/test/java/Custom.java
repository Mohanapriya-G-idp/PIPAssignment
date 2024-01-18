import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Custom extends Base implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {

        failedCases(result.getMethod().getMethodName());
        sendEmail();
    }
}
