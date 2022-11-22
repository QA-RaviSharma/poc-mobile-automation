Feature: Allow user to add product in marketcube using form

  User as Seller/Vendor/System-Admin
  want to add a product using add product form in marketcube app

  Scenario: User logins as seller in marketcube app and want to add product using form
    When User logins in marketcube successfully as operator and able to see the home page
    When User goes to add product form page in marketcube
    And User enters valid details of the product on add product page
    And User clicks on save and next button on add product page
    Then User should be able to add product in marketcube using form successfully

  Scenario: User logins as vendor in marketcube app and want to add product using form
    When User logins in marketcube successfully as vendor and able to see the home page
    When User goes to add product form page in marketcube
    And User enters valid details of the product on add product page
    And User clicks on save and next button on add product page
    Then User should be able to add product in marketcube using form successfully

  Scenario: Add product using form scenario used in other scenarios
    When User logins in marketcube successfully as operator and able to see the home page
    When User enters valid details and adds the product in marketcube successfully
