import {
  LoginPageEyes,
  LoginPageDependencies,
  LoginPageHands,
} from "../robots/login/loginRobot";
import { PIMEyes, PIMHands } from "../robots/pim/pimRobot";
import {
  DirectoryEye,
  DirectoryHand,
} from "../robots/directory/directoryRobot";

const appLogin = new LoginPageDependencies();
const loginEye = new LoginPageEyes();
const loginHand = new LoginPageHands();
const pimEye = new PIMEyes();
const pimHand = new PIMHands();
const directoryEye = new DirectoryEye();
const directoryHand = new DirectoryHand();

describe(
  "Login OrangeHRM and verify the added employee",
  () => {
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
