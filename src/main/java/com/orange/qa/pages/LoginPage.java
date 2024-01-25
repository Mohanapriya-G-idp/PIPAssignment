package com.orange.qa.pages;

import com.orange.qa.base.TestBase;
import com.orange.qa.utlis.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends TestBase {
    public LoginPage() {
        PageFactory.initElements(driver,this);
    }

@FindBy(css = "img[alt='company-branding']")
    WebElement titleLogo;

   @FindBy(name = "username")
    WebElement user;
   @FindBy(name = "password")
    WebElement pwd;
   @FindBy(xpath = "//button[text()=' Login ']")
    WebElement loginBtn;
   @FindBy(css= ".oxd-userdropdown-tab > img[alt='profile picture']")
   WebElement homePage;
   @FindBy(xpath = "//h6[text()='Dashboard']")
   WebElement dashboard;

   public void validateLoginPage(){
       TestUtil.waitForELementToAppear(titleLogo);
       Boolean flag = titleLogo.isDisplayed();
       Assert.assertTrue(flag);
   }

   public void enterLoginDetails(){
       TestUtil.waitForELementToAppear(user);
       user.sendKeys(properties.getProperty("username"));
       TestUtil.waitForELementToAppear(pwd);
       pwd.sendKeys(properties.getProperty("password"));
       TestUtil.waitForELementToAppear(loginBtn);
       loginBtn.click();
   }
   public void validateHomePage(){
       TestUtil.waitForELementToAppear(homePage);
       boolean userIcon = homePage.isEnabled();
       Assert.assertTrue(userIcon);
     //  boolean displayed = dashboard.isDisplayed();

   }

}
