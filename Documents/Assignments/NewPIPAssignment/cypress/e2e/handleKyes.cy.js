describe("Handle Using Keyboard", () => {
  it("should fill and submit the form using keyboard shortcuts", () => {
    cy.visit("https://demoqa.com/automation-practice-form");

    cy.get("#firstName").type("{selectall}").type("John");
    cy.get("#lastName").type("{selectall}").type("Doe");

    cy.get("#userEmail").type("{tab}").type("john.doe@example.com");
    cy.get("#gender-radio-1").type("{tab}{space}");

    cy.get("#submit").type("{tab}{enter}");

    cy.url().should("include", "confirmation");
  });
});
