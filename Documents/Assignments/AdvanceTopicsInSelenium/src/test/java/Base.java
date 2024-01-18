import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports extent;
    public static ExtentTest test;


    public void failedCases(String methodName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File trg = new File("src/screenshots/failCase_" + methodName + "_" + ".jpg");
        try {
            FileUtils.copyFile(src, trg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void readData() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmail()  {
        readData();
        MimeBodyPart mimehtml = new MimeBodyPart();
        MimeBodyPart mimeScreen = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        String report ="/home/mohag/Documents/Assignments/AdvanceTopicsInSelenium/reports/Extent_Report.html";
        DataSource html = new FileDataSource(report);
        try {
            mimehtml.setDataHandler( new DataHandler(html));
            mimehtml.setFileName("Extent_report.html");
            multipart.addBodyPart(mimehtml);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        File sreenshot = new File("reports/screenshots");
        for (File ss : sreenshot.listFiles()){
            DataSource screen = new FileDataSource(ss);
            try {
                mimeScreen.setDataHandler(new DataHandler(screen));
                mimeScreen.setFileName(ss.getName());
                multipart.addBodyPart(mimeScreen);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        String user = prop.getProperty("gmail_username");
        String pwd = prop.getProperty("gmail_password");
        String sendTo= prop.getProperty("gmail_to");
        String port = prop.getProperty("gmail_port");

        MultiPartEmail mail = new MultiPartEmail();
        mail.setHostName("smtp.googlemail.com");
        mail.setSmtpPort(Integer.parseInt(port));
        mail.setAuthenticator(new DefaultAuthenticator(user, pwd));
        mail.setSSLOnConnect(true);
        try {
            mail.setFrom(user);
            mail.setMsg("This is test mail with attachment");
            mail.addTo(sendTo);
            mail.setSubject("Test");
            mail.setContent((MimeMultipart) multipart);
            mail.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }

}

