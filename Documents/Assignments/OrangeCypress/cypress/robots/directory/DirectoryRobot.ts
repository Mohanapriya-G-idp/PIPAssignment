import { BaseDependencies, BaseEyes, BaseHands } from "../BaseRobot";

var inputName: any;
export class DirectoryEye extends BaseEyes {
  verifyDirectoryPage() {
    this.seesTextWithClass("oxd-topbar-header-breadcrumb-module", "Directory");
  }
  verifyNewEmployee() {
    cy.fixture("./employee.json").then((data) => {
      inputName = data.firstname;
    });
    cy.get("p[class*='orangehrm-directory-card-header']").each((cols) => {
      cy.wrap(cols)
        .invoke("text")
        .then((response) => {
          const fName = response.split(" ")[0].trim();
          expect(fName).to.equal(inputName);
        });
    });
  }
}
export class DirectoryHand extends BaseHands {
  clickOnDirectoryIcon() {
    this.clickOnDomElement(
      "li:nth-child(9) > a:first-child > span:nth-child(2)"
    );
  }
}
