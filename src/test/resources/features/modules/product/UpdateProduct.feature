Feature: Allow user to update inventory and fulfillment details in the added product in marketcube

  Scenario: User logins as seller in marketcube app and want to update the inventory in the added product
    When User logins in marketcube successfully as operator and able to see the home page
    When User enters valid details and adds the product in marketcube successfully
    When User enters valid inventory details
    And User clicks on save and next button to update the inventory details
    Then Inventory details should be updated in the product successfully

  Scenario: User logins as vendor in marketcube app and want to update the inventory in the added product
    When User logins in marketcube successfully as vendor and able to see the home page
    When User enters valid details and adds the product in marketcube successfully
    When User enters valid inventory details
    And User clicks on save and next button to update the inventory details
    Then Inventory details should be updated in the product successfully

  Scenario: User logins as seller in marketcube app and want to update the fulfillment in the added product
    When User logins in marketcube successfully as operator and able to see the home page
    When User enters valid details and adds the product in marketcube successfully
    When User enters valid fulfillment details
    And User clicks on save and next button to update the fulfillment details
    Then Fulfillment details should be updated in the product successfully

  Scenario: User logins as vendor in marketcube app and want to update the fulfillment in the added product
    When User logins in marketcube successfully as vendor and able to see the home page
    When User enters valid details and adds the product in marketcube successfully
    When User enters valid fulfillment details
    And User clicks on save and next button to update the fulfillment details
    Then Fulfillment details should be updated in the product successfully
