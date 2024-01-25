import{defineConfig } from "cypress";
export default defineConfig({


e2e: {
    baseUrl : "",
    watchForFileChanges: false,
    setupNodeEvents(on, config){

    },
},

});