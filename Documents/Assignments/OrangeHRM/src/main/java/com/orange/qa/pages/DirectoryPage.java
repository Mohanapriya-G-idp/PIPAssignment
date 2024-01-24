package com.orange.qa.pages;

import com.orange.qa.base.TestBase;
import com.orange.qa.utlis.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;

public class DirectoryPage extends TestBase {

    public DirectoryPage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='Directory']")
    WebElement directoryIcon;

    @FindBy(xpath = "//h6[text()='Directory']")
    WebElement directoryPage;

    @FindAll(
            @FindBy(xpath = "//div[@class='oxd-grid-7']//div//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']")
    )
    List<WebElement> namesList;

    public void validateDirectoryPage(){
        directoryIcon.click();
        TestUtil.waitForELementToAppear(directoryPage);
        Assert.assertTrue(directoryPage.isDisplayed());
    }


    public void validateAddedEmployee(String fname){
        Iterator<WebElement> it = namesList.iterator();

        while (it.hasNext()){
            WebElement names = it.next();
            String fullname = names.getText();
            String[] s = fullname.split(" ");
            String firstName = s[0];
            Assert.assertEquals(firstName,fname,"Newly added employee details is listed");
        }
    }




}


