import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeTest
    public void launchApp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void dragAndDrop(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement dragEle = driver.findElement(By.cssSelector("div[id='draggable']"));
        wait.until(ExpectedConditions.visibilityOf(dragEle));
        WebElement dropEle = driver.findElement(By.id("droppable"));
        wait.until(ExpectedConditions.visibilityOf(dropEle));
        Actions a = new Actions(driver);
        a.dragAndDrop(dragEle, dropEle).build().perform();
        driver.switchTo().defaultContent();

    }
    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();

    }


}
