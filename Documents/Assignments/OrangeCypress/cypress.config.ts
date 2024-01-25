const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    testIsolation: false ,
    baseUrl: "https://opensource-demo.orangehrmlive.com",
    setupNodeEvents(on: any, config: any) {},
  },
});
