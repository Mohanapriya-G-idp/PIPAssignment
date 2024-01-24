package com.orange.qa.test;

import com.orange.qa.base.TestBase;
import com.orange.qa.pages.EmploymentPage;
import com.orange.qa.pages.LoginPage;
import com.orange.qa.utlis.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmploymentPageTest extends TestBase {
    LoginPage loginPage;
    EmploymentPage employmentPage;

    public EmploymentPageTest() {
super();
    }

    @Test
    public void loginIntoAccount(){
        loginPage = new LoginPage();
        loginPage.enterLoginDetails();
    }
    @Test(priority = 1)
    public void verifyEmploymentPage(){
        employmentPage = new EmploymentPage();
        employmentPage.validateEmploymentPage();
    }
    @DataProvider
    public Object[][] readData(){
        return TestUtil.readCsv().toArray(new Object[0][]);
    }
    @Test(dataProvider = "readData",priority = 2)
    public void addNewEmployee(String firstName,String middleName,String lastName){

        employmentPage.addNewEmployee(firstName,middleName,lastName);
        inputName = firstName;
    }
    @Test(priority = 3)
    public void verifyAddedEmployee(){
        employmentPage.validateAddedEmployee(inputName);
    }
}
