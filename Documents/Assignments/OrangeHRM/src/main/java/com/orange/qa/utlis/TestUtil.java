package com.orange.qa.utlis;

import com.opencsv.CSVReader;
import com.orange.qa.constants.Constants;
import com.orange.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestUtil extends TestBase {

    public static List<String[]> datas;
    public static DataFormatter df;

    public static void waitForELementToAppear(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static List<String[]> readCsv(){
        try {
            FileReader fr = new FileReader(Constants.CSV_FILE_PATH);
            CSVReader reader = new CSVReader(fr);
            datas = reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        datas.remove(0);
        return datas;
    }
public static String getScreenShot(String methodname){
    TakesScreenshot ts = (TakesScreenshot) driver;
    File src = ts.getScreenshotAs(OutputType.FILE);
    File dest = new File(Constants.SCREENSHOT_FILE_PATH+methodname+".jpg");
    try {
        FileUtils.copyFile(src,dest);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return dest.getAbsolutePath();
}

public static void sendMail(){
    MimeBodyPart mimeHtml = new MimeBodyPart();
    Multipart multipart = new MimeMultipart();
    String report = Constants.REPORT_FILE_PATH;
    DataSource htmlreport = new FileDataSource(report);
    try {
        mimeHtml.setDataHandler(new DataHandler(htmlreport));
        mimeHtml.setFileName(Constants.REPORT_NAME);
        multipart.addBodyPart(mimeHtml);
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    String user = properties.getProperty("gmailUserName");
    String pwd = properties.getProperty("gmailPassword");
    String receiver = properties.getProperty("gmailSentTo");
    String port = properties.getProperty("gmailPort");
    MultiPartEmail mail = new MultiPartEmail();
    mail.setHostName(properties.getProperty("hostName"));
    mail.setSmtpPort(Integer.parseInt(port));
    mail.setAuthenticator(new DefaultAuthenticator(user,pwd));
    mail.setSSLOnConnect(true);

    try {
        mail.setFrom(user);
        mail.setMsg(Constants.MAIL_MSG);
        mail.addTo(receiver);
        mail.setContent((MimeMultipart) multipart);
        mail.send();
    } catch (EmailException e) {
        throw new RuntimeException(e);
    }
}

}
