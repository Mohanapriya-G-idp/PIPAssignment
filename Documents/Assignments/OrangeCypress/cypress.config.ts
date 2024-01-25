const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      return config;
    },
  },
  env: {
    baseUrl:
      "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",
  },
});
