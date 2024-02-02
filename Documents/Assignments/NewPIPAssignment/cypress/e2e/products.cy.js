describe("Shopping Cart Tests", () => {
  beforeEach(() => {
   
    cy.visit("https://react-shopping-cart-67954.firebaseapp.com/");
  });

  it("should display correct data on the page", () => {
    
    cy.intercept("GET", "https://react-shopping-cart-67954.firebaseio.com/products.json").as(
      "getProducts"
    );

    
    cy.wait("@getProducts");

    cy.get("div[class='sc-124al1g-1 csvtPz']").should("have.length", 3);

  
    cy.contains("div[class='sc-124al1g-1 csvtPz']", "Product 1").should("exist");
    cy.contains("div[class='sc-124al1g-1 csvtPz']", "Product 2").should("exist");
    cy.contains("div[class='sc-124al1g-1 csvtPz']", "Product 3").should("exist");
  });

  it("should handle error state when products cannot be loaded", () => {
   
    cy.intercept("GET", "**/api/products", { statusCode: 500 }).as(
      "getProductsError"
    );

  
    cy.wait("@getProductsError");

    
    cy.contains("Error loading products").should("exist");
  });

  
});
