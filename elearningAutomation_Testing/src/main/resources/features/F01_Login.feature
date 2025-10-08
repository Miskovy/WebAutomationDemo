Feature: User is able to login
  Scenario: User enters credentials to login
    When the user is in the login page
    And the user enters the email "mazenkhairy48@yahoo.com" and password "12345678"
    Then error message should appear