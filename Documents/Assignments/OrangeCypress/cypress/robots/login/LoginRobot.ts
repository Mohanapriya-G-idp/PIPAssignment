import { data } from "cypress/types/jquery";
import { BaseHands, BaseEyes, BaseDependencies } from "../BaseRobot";

export class LoginPageDependencies extends BaseDependencies {
  visitPage() {
    this.accessUrl(``);
  }
}

export class LoginPageEyes extends BaseEyes {
  verifyLoginPage() {
    this.seesTextWithClass(
      "oxd-text.oxd-text--h5.orangehrm-login-title",
      "Login"
    );
  }

  verifyHomePage() {
    this.seesClassVisible("oxd-userdropdown-name");
    this.seesClassVisible("oxd-brand-banner");
  }
}

export class LoginPageHands extends BaseHands {
  enterLoginDetails() {
    cy.fixture("./login.json").then((data) => {
      this.typeTextonDom("name", "username", data.username);
      this.typeTextonDom("name", "password", data.password);
      this.clickOnDomElementByAttribute("type", "submit");
      cy.intercept(
        "GET",
        "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"
      ).as("login");
      cy.wait("@login").then((res) => {
        res.response?.statusCode == 200;
      });
    });
  }
}
