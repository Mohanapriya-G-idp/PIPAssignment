package com.orange.qa.test;

import com.orange.qa.base.TestBase;
import com.orange.qa.pages.DirectoryPage;
import com.orange.qa.pages.LoginPage;
import com.orange.qa.utlis.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DirectoryPageTest extends TestBase {
LoginPage loginPage;
DirectoryPage directoryPage;
    public DirectoryPageTest() {
    super();
    }
    @Test
    public void loginIntoAccount(){
        loginPage = new LoginPage();
        loginPage.enterLoginDetails();
    }
    @Test(priority = 1)
    public void verifyDirectoryPage(){
        directoryPage = new DirectoryPage();
        directoryPage.validateDirectoryPage();
    }
    @DataProvider
    public Object[][] readData(){
        return TestUtil.readCsv().toArray(new Object[0][]);
    }
    @Test(dataProvider = "readData",priority = 2)
    public void verifyAddedEmployee(String firstName,String middleName,String lastName){
        inputName=firstName;
        directoryPage.validateAddedEmployee(inputName);
}
}
