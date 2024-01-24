package com.orange.qa.pages;

import com.orange.qa.base.TestBase;
import com.orange.qa.utlis.TestUtil;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class EmploymentPage extends TestBase {


    public EmploymentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimIcon;
    @FindBy(css = "button[class*='oxd-button'] >i")
    WebElement addIcon;
    @FindBy(xpath = "//h6[text()='PIM']")
    WebElement pimPage;

    @FindBy(name = "firstName")
    WebElement fName;
    @FindBy(name = "middleName")
    WebElement mName;
    @FindBy(name = "lastName")
    WebElement lName;

    @FindBy(css = "button[class*='secondary orangehrm-left-space']")
    WebElement saveIcon;
    @FindAll(
            @FindBy(css = "div[class*='oxd-table-row--clickable'] > div:nth-child(3)")
    )
    List<WebElement> tableFNames;
    @FindAll(
            @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card']")
    )
    List<WebElement> rowSize;
    @FindBy(xpath = "button[class*='oxd-pagination-page-item--previous-next']")
    WebElement nextIcon;


    public void validateEmploymentPage() {
        pimIcon.click();
        TestUtil.waitForELementToAppear(pimPage);
        boolean flag = pimPage.isDisplayed();
        Assert.assertTrue(flag);
    }

    public void addNewEmployee(String fname, String mname, String lname) {
        addIcon.click();
        fName.sendKeys(fname);
        TestUtil.waitForELementToAppear(mName);
        mName.sendKeys(mname);
        TestUtil.waitForELementToAppear(lName);
        lName.sendKeys(lname);
        TestUtil.waitForELementToAppear(saveIcon);
        saveIcon.click();


    }

    public void validateAddedEmployee(String fname) {
        TestUtil.waitForELementToAppear(pimIcon);
        pimIcon.click();
        for (WebElement element:tableFNames){
            Assert.assertEquals(element.getText(),fname);
            if(nextIcon.isEnabled()){
                TestUtil.waitForELementToAppear(nextIcon);
                nextIcon.click();}
            else {
                break;
            }
        }
    }
}
