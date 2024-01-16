package com.demo.qa.pages;

import com.demo.qa.base.TestBase;
import com.demo.qa.utils.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends TestBase {
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class='home-banner']")
    WebElement homePage;

    @FindBy(xpath = "//h5[text()='Elements']")
    WebElement elementIcon;

    public void validateHomePage(){
        TestUtil.waitToElementToAppear(homePage);
        Boolean image = homePage.isDisplayed();
        Assert.assertTrue(image);

    }
    public ElementPage clickOnElementIcon(){
        TestUtil.waitToElementToAppear(elementIcon);
        elementIcon.click();
        return new ElementPage();
    }

}
