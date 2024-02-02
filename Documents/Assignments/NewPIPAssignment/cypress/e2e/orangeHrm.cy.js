describe("OrangeHRM test", () => {
  before(() => {
    cy.contains("Admin").click();
    cy.contains("User Management ").click();
    cy.visit("/");
    cy.fixture("./login.json").then((data) => {
      cy.get("input[name='username']").type(data.username);
      cy.get("input[name='password']").type(Data.password);
      cy.get("button[type='submit']").click();
    });
  });
  it("login", () => {
    cy.title().should("contains", "OrangeHRM");
    const curDate = Cypress.moment().format("DDMMYYYY");
    cy.window().then((response) => {
      const userId = `user_${curDate}`;
      response.localStoage.setItem("userId", userId);
    });
  });
  it("Create user", () => {
    cy.window().then((response) => {
      const userId = response.localStoage.getItem("userId");
    });
    cy.get("ul[class='oxd-dropdown-menu'] li").click();
    cy.get(
      "button[class='oxd-button oxd-button--medium oxd-button--secondary']"
    ).click();
    cy.get(".oxd-select-text-input").click();
    cy.contains("Admin").click();
    cy.get(
      ".oxd-select-text.oxd-select-text--active.oxd-select-text--error"
    ).click();
    cy.contains("Enabled").click();
    cy.fixture("./createUser.json").then((data) => {
      cy.get("input[type='password']").type(data.password);
      cy.get("input[placeholder='Type for hints...']").type(data.employeeName);
      cy.contains("Username").type(`user_${userID}`);
      cy.contains("Confirm Password").type(data.confirm);
      cy.get("button[type='submit']").click();
      cy.get(".oxd-toast-container.oxd-toast-container--bottom").should(
        "contain.text",
        "Successfully Added"
      );
    });
    response.localStoage.setItem("addedUserId", userId);
  });

  it("Delete user", () => {
    cy.window().then((response) => {
      const addedUserId = response.localStoage.getItem("addedUserId");
    });
    cy.get(
      "div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']"
    ).type(`user_${addedUserId}`);
    cy.get("button[type='submit']").click();
    cy.get(".oxd-table-body div div div")
      .contains(`user_${addedUserId}`)
      .check();
    cy.get(
      "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(6) > div:nth-child(1) > button:nth-child(1) > i:nth-child(1)"
    ).click();
    cy.get(".oxd-toast-container.oxd-toast-container--bottom").should(
      "contain.text",
      "Successfully Deleted"
    );
  });
});
