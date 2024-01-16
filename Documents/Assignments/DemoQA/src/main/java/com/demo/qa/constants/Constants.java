package com.demo.qa.constants;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class Constants {
    public static final String PROP_FILE_PATH = "src/main/java/com/demo/qa/config/config.properties";
    public static String FIREFOX = "FireFox";
    public static String EDGE = "Edge";
    public static int IMPLICIT_WAIT = 10;
    public static int PAGE_LOAD_WAIT = 20;
    public static int EXPLICIT_WAIT = 10;
    public static String ELEMENT = "Elements";
    public static String TEXT_BOX = "Text Box";

    public static String CSV_FILE_PATH = "src/main/java/com/demo/qa/testdata/datas.csv";

    public static String FRAME_CONTENT = "This is a sample page";
    public static String PAGE_TITLE = "DEMOQA";
    public static String EXCEL_FILE_PATH = "src/main/java/com/demo/qa/testdata/datas.xlsx";
    public static String SCREENSHOT_PATH = "/home/mohag/Documents/Assignments/DemoQA/src/test/screenshot/";
    public static String REPORT_PATH = "/home/mohag/Documents/Assignments/DemoQA/Extent_Report.html";
    public static String REPORT_NAME = "Extent_Report.html";
    public static String MAIL_MSG = "Failed test cases with screenshots";

}
