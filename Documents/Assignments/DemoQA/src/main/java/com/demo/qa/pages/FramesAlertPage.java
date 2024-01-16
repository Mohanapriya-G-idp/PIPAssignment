package com.demo.qa.pages;

import com.demo.qa.base.TestBase;
import com.demo.qa.constants.Constants;
import com.demo.qa.utils.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;

public class FramesAlertPage extends TestBase {

    public FramesAlertPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[text()='Alerts, Frame & Windows']")
    WebElement framesWindows;
    @FindBy(xpath = "//span[text()='Frames']")
    WebElement framesTab;
    @FindBy(id = "frame1")
    WebElement frame1;
    @FindBy(id = "frame2")
    WebElement frame2;
    @FindBy(xpath = "//div[text()='Frames']")
    WebElement framePage;

    @FindBy(xpath = "//span[text()='Browser Windows']")
    WebElement windowsTab;
    @FindBy(xpath = "//div[text()='Browser Windows']")
    WebElement windowPage;
    @FindBy(id = "tabButton")
    WebElement newTab;

    @FindBy(id = "sampleHeading")
    WebElement childTab;
    @FindBy(xpath = "//span[text()='Alerts']")
    WebElement alertsTab;
    @FindBy(id = "promtButton")
    WebElement promptAlertBtn;
    @FindBy(id = "promptResult")
    WebElement result;


    public void validateFrames() {
        TestUtil.waitToElementToAppear(frame1);
        driver.switchTo().frame(frame1);
        String ps = driver.getPageSource();
        Assert.assertTrue(ps.contains(Constants.FRAME_CONTENT));
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.getTitle().contains(Constants.PAGE_TITLE));
    }

    public void validateWindows() {
        String parentwindow = driver.getWindowHandle();
        TestUtil.waitToElementToAppear(newTab);
        newTab.click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String win : windowHandles) {
            if (!win.equals(parentwindow)) {
                driver.switchTo().window(win);
                break;
            }
        }
        TestUtil.waitToElementToAppear(childTab);
        Boolean flag = childTab.isDisplayed();
        Assert.assertTrue(flag);
        driver.switchTo().window(parentwindow);
    }

    public void goToFramesPage() {
        TestUtil.waitToElementToAppear(framesWindows);
        framesWindows.click();
        TestUtil.waitToElementToAppear(framesTab);
        framesTab.click();
        Boolean f = framePage.isDisplayed();
        Assert.assertTrue(f);

    }

    public void goToFrameTab() {
        TestUtil.waitToElementToAppear(framesTab);
        framesTab.click();
        Boolean f = framePage.isDisplayed();
        Assert.assertTrue(f);
    }

    public void goToWindowTab() {
        TestUtil.waitToElementToAppear(windowsTab);
        windowsTab.click();
        Boolean w = windowPage.isDisplayed();
        Assert.assertTrue(w);

    }

    public void goToAlertsTab() {
        alertsTab.click();
        TestUtil.waitToElementToAppear(promptAlertBtn);
    }

    public void validatePromptAlert() {
        promptAlertBtn.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(Constants.ELEMENT);
        alert.accept();
        String alt = result.getText();
        Assert.assertTrue(alt.contains(Constants.ELEMENT));
    }
}
