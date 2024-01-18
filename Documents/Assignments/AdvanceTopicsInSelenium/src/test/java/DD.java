import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;


import java.io.FileInputStream;
import java.io.IOException;

public class DD extends Base{
    ExtentReports extent;
    ExtentTest test;
    @BeforeTest
    public void startReport(){
        extent = new ExtentReports();
        ExtentSparkReporter sp = new ExtentSparkReporter("ExtentReport.html");
        extent.attachReporter(sp);
    }

    @Test(dataProvider = "testData")
    public void dataDriven(String tdata, String uname, String pwd) {
        readData();
        String auth = prop.getProperty("Author");
        String category = prop.getProperty("Category");
        test = extent.createTest("Data Driven").assignAuthor(auth).assignCategory(category);
        System.out.println(tdata + uname + pwd);


    }

    @DataProvider(name = "testData")
    public Object[][] getData() throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream fis = new FileInputStream("src/test/resources/datas.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row.getCell(j + 1);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }

        return data;

    }
@AfterTest
    public void reports(){
        extent.flush();
}

}
