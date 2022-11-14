Feature: Allow user to login on marketcube system

  User as Operator/Vendor/System-Admin
  want to login into marketcube system and access the platform

  Scenario: User logins as operator into marketcube system with valid details
    When User enters valid details in email and password fields
      | email                | password              |
      | operatorValidEmailId | operatorValidPassword |
    And User clicks on login button on marketcube login page
    Then User should be logins in marketcube successfully and able to see the home page

  Scenario: User logins as vendor into marketcube system with valid details
    When User enters valid details in email and password fields
      | email              | password            |
      | vendorValidEmailId | vendorValidPassword |
    And User clicks on login button on marketcube login page
    Then User should be logins in marketcube successfully and able to see the home page

  Scenario: User logins as system-admin into marketcube system with valid details
    When User enters valid details in email and password fields
      | email             | password           |
      | adminValidEmailId | adminValidPassword |
    And User clicks on login button on marketcube login page
    Then User should be logins in marketcube successfully and able to see the home page

  Scenario Outline: User tries to login into marketcube system with invalid details
    When User enters valid details in email and password fields
      | email   | password   |
      | <email> | <password> |
    Then User should not be login in marketcube successfully and get validation messages
      | Password is required.           |
      | Email is required.              |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Please enter a valid email.     |
      | Incorrect credentials provided. |

    Examples:
      | email                           | password              |
      | abc@domain.com                  | empty                 |
      | empty                           | pass@1234             |
      | .test@domain.com                | pass@1234             |
      | abc\abc@domain.com              | pass@1234             |
      | abc”test”email@domain.com       | pass@1234             |
      | a”b(c)d,e:f;gi[j\k]l@domain.com | pass@1234             |
      | A@b@c@domain.com                | pass@1234             |
      | abc-@domain.com                 | pass@1234             |
      | abc#def@domain.com              | pass@1234             |
      | domain.com                      | pass@1234             |
      | test@domain..com                | pass@1234             |
      | abc.def@domain.c                | pass@1234             |
      | abc.def@domain#archive.com      | pass@1234             |
      | abc.def@domain                  | pass@1234             |
      | abc.def@domain..com             | pass@1234             |
      | userValidEmailId                | diffUserValidPassword |

  Scenario: One liner scenario for login as operator into marketcube system with valid details
    When User logins in marketcube successfully as operator and able to see the home page

  Scenario: One liner scenario for login as vendor into marketcube system with valid details
    When User logins in marketcube successfully as vendor and able to see the home page

  Scenario: One liner scenario for login as system-admin into marketcube system with valid details
    When User logins in marketcube successfully as system-admin and able to see the home page