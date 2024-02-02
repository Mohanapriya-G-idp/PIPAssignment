describe("Data driven Tesing", () => {
  let userdatas;
  before(() => {
    cy.visit("https://practicetestautomation.com/practice-test-login/");
    cy.fixture("./input.json").then((data) => {
      userdatas = data;
    });
  });

  userdatas.forEach((credentials) => {
    it(credentials.scenarioname, () => {
      cy.get("#username").type(credentials.username);
      cy.get("#password").type(credentials.password);
      cy.get("#submit").click();
      cy.get(credentials.assertionid).
      should("contain.text",credentials.assertion);
    });
  });
});
