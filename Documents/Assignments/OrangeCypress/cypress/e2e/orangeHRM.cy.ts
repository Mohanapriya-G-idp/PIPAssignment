import cypress = require("cypress");
import {
  LoginPageEyes,
  LoginPageDependencies,
  LoginPageHands,
} from "/home/mohag/Documents/Assignments/OrangeCypress/cypress/robots/login/LoginRobot";
import {
  PIMEyes,
  PIMHands,
} from "/home/mohag/Documents/Assignments/OrangeCypress/cypress/robots/pim/PIMRobot";
import {
  DirectoryEye,
  DirectoryHand,
} from "/home/mohag/Documents/Assignments/OrangeCypress/cypress/robots/directory/DirectoryRobot";

describe(
  "Login OrangeHRM and verify the added employee",
  { testIsolation: false },
  () => {
    const appLogin = new LoginPageDependencies();
    const loginEye = new LoginPageEyes();
    const loginHand = new LoginPageHands();
    const pimEye = new PIMEyes();
    const pimHand = new PIMHands();
    const directoryEye = new DirectoryEye();
    const directoryHand = new DirectoryHand();

    before(() => {
      cy.clearAllCookies();
      cy.clearAllSessionStorage();
      appLogin.visitPage();
    });

    it("login with credentails", () => {
      loginEye.verifyLoginPage();
      loginHand.enterLoginDetails();
      loginEye.verifyHomePage();
    });
    it("Add new employee and verify", () => {
      pimHand.clickOnPIMIcon();
      pimEye.verifyPIMPage();
      pimHand.addNewEmployeeDetails();
      pimHand.clickOnPIMIcon();
      pimEye.verifyNewEmployee();
    });
    it("verify the added employee in directory", () => {
      directoryHand.clickOnDirectoryIcon();
      directoryEye.verifyDirectoryPage();
      directoryEye.verifyNewEmployee();
    });
  }
);
