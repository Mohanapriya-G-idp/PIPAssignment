const path = require("path"); 
const isPalindrome = require("../cypress/methods/palindrome.js");

describe("Palindrome", () => {
  it("Check whether the string is palindrome", () => {
    const input = "Madam";

    cy.exec(
      `node ${path.resolve(
        __dirname,
        "../cypress/methods/palindrome.js"
      )} "${input}"`
    ).then((result) =>{
      cy.log(result.stdout);

    });
  });
});
