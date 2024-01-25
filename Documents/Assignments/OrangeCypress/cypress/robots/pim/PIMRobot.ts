import { BaseDependencies, BaseEyes, BaseHands } from "../BaseRobot";
var inputText: any;

export class PIMEyes extends BaseEyes {
  verifyPIMPage() {
    this.seesTextWithClass("oxd-topbar-header-breadcrumb-module", "PIM");
  }
  verifyNewEmployee() {
    cy.get(
      "div[class='oxd-table-body'] >div.oxd-table-card >div>div[class ='oxd-table-cell oxd-padding-cell']:nth-child(3)"
    ).each((cols) => {
      cy.wrap(cols)
        .invoke("text")
        .then((response) => {
          expect(response.trim()).to.equal(inputText);
        });
    });
  }
}

export class PIMHands extends BaseHands {
  clickOnPIMIcon() {
    this.clickOnDomElement(
      "li.oxd-main-menu-item-wrapper > a.oxd-main-menu-item :nth-child(3)"
    );
    this.wait(3000);
  }
  addNewEmployeeDetails() {
    this.clickOnDomElement(
      "button[class='oxd-button oxd-button--medium oxd-button--secondary']"
    );
    cy.fixture("./employee.json").then((data) => {
      inputText = data.firstname;
      this.typeTextonDom("name", "firstName", data.firstname);
      this.typeTextonDom("name", "middleName", data.middlename);
      this.typeTextonDom("name", "lastName", data.lastname);
      this.clickOnDomElement("button[type='submit']");
      cy.intercept(
        "GET",
        "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/89"
      ).as("newEmp");
      cy.wait("@newEmp").then((response) => {
        response.response?.statusCode == 200;
      });
    });
  }
}
