package com.demo.qa.utils;

import com.demo.qa.base.TestBase;
import com.demo.qa.constants.Constants;
import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestUtil extends TestBase {
    static CSVReader csvReader;
    static List<String[]> data;
    DataFormatter df;
    static String[][] datas;

    public TestUtil() {
        super();
    }

    public static void waitToElementToAppear(WebElement webElement) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    public static List<String[]> getData() {
        try {
            FileReader read = new FileReader(Constants.CSV_FILE_PATH);
            csvReader = new CSVReader(read);
            data = csvReader.readAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        data.remove(0);
        return data;
    }

    public String[][] readData() {
        try {
            FileInputStream fs = new FileInputStream(Constants.EXCEL_FILE_PATH);
            XSSFWorkbook wb = new XSSFWorkbook(fs);
            XSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = sheet.getRow(0).getLastCellNum();
            datas = new String[lastRowNum][lastCellNum];
            for (int i = 1; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < lastCellNum; j++) {
                    XSSFCell cell = row.getCell(j + 1);
                    datas[i][j] = df.formatCellValue(cell);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    public static String getScreenShot(String methodname){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dst = new File(Constants.SCREENSHOT_PATH+methodname+".jpg");
        try {
            FileUtils.copyFile(src,dst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dst.getAbsolutePath();
    }
    public static void sendMail(){
        MimeBodyPart html = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        String report = Constants.REPORT_PATH;
        DataSource htmlReport = new FileDataSource(report);
        try {
            html.setDataHandler(new DataHandler(htmlReport));
            html.setFileName(Constants.REPORT_NAME);
            multipart.addBodyPart(html);
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

