Feature: Amazon without Login credentials

  Background:
    Given open the application url
  Scenario: Verify Home Page
    Given verify title of Home page
    When clicks on TodayDeal

  Scenario: Clicks on Third item on TodayDeal
    Given verify title of Deal Page
    When clicks on Third Deal
    And add product to cart