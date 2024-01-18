import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Listeners(Custom.class)
public class Task extends Base {
    @BeforeSuite
    public void launchBrowser() {
        readData();
      //  WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        String url = prop.getProperty("App_url");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @BeforeTest
    public void startReport() {
        extent = new ExtentReports();
        ExtentSparkReporter sp = new ExtentSparkReporter("reports/Extent_Report.html");
        extent.attachReporter(sp);
    }

        @Test(enabled = false)
    public void writeData() throws IOException {
        String csv = "src/test/resources/login.csv";
        FileWriter writer = new FileWriter(csv);
        writer.append("Email, password \n");
        writer.append("priyagopalakrishnann15@gmail.com,Mohanapriya257 \n");
        writer.close();

    }
    @DataProvider
    public String[][] read() throws IOException, CsvException {
        FileReader reader = new FileReader("src/test/resources/login.csv");
        CSVReader read = new CSVReader(reader);
        List<String[]> data = read.readAll();
        data.remove(0);
        return data.toArray(new String[0][]);
    }

    @Test(dataProvider = "read")
    public void login(String user, String pass) {
        readData();
        String auth = prop.getProperty("Author");
        String category = prop.getProperty("Category");
        test = extent.createTest("Data Driven").assignAuthor(auth).assignCategory(category);
        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        WebElement mail = driver.findElement(By.id("ap_email"));
        mail.sendKeys(user);
        driver.findElement(By.id("continue")).click();
        WebElement pwd = driver.findElement(By.id("ap_password"));
        pwd.sendKeys(pass);
        driver.findElement(By.id("signInSubmit")).click();
        Assert.assertFalse(true);

    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
        driver.quit();

    }
}
