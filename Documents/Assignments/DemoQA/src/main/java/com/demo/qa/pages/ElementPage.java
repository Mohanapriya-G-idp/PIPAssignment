package com.demo.qa.pages;

import com.demo.qa.base.TestBase;
import com.demo.qa.constants.Constants;
import com.demo.qa.utils.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ElementPage extends TestBase {

    public ElementPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (css ="div[class='main-header']")
    WebElement page;

    @FindBy(xpath = "//span[text()='Text Box']")
    WebElement textBox;

    @FindBy(id ="userName")
    WebElement fullName;
    @FindBy(id = "userEmail")
    WebElement email;
    @FindBy(id ="currentAddress")
    WebElement curAddress;

    @FindBy(id = "permanentAddress")
    WebElement perAddress;
    @FindBy(id="submit")
    WebElement submitBtn;
    @FindBy(xpath = "//p[@id='name']")
    WebElement result;

    public void validateElementPage(){
        TestUtil.waitToElementToAppear(page);
        Assert.assertEquals(page.getText(), Constants.ELEMENT);
    }
    public void clickOnTextBox(){
        textBox.click();
    }

    public void validateTextPage(){
        TestUtil.waitToElementToAppear(page);
        Assert.assertEquals(page.getText(),Constants.TEXT_BOX);
    }
    public void enterCredentialsInForm(String uname,String mailID,String addr){
        fullName.sendKeys(uname);
        email.sendKeys(mailID);
        curAddress.sendKeys(addr);
        perAddress.sendKeys(addr);
        submitBtn.click();
    }
    public String validateTextEntered(){
        TestUtil.waitToElementToAppear(result);
        return result.getText();
    }


}
