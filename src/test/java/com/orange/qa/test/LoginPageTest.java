package com.orange.qa.test;


import com.orange.qa.base.TestBase;
import com.orange.qa.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    public LoginPageTest() {

        super();
    }
    @Test
    public void verifyLoginPage(){
        loginPage = new LoginPage();
        loginPage.validateLoginPage();
    }
    @Test(priority = 1)
    public void applicationLogin(){
        loginPage.enterLoginDetails();
    }
    @Test(priority = 2)
    public void verifyHomePage(){
        loginPage.validateHomePage();
    }
}
