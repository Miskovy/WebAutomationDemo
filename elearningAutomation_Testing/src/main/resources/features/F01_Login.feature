Feature: User is able to login
  @ExpectToFail
  Scenario: User enters credentials to login
    When the user is in the login page
    And the user enters the email "mazenkhairy48@yahoo.com" and password "12345678"
    Then error message should appear
  @ExpectToSuccess
  Scenario: User is able to login with a successful credentials
    When the user is in the login page
    And the user enters the email "" and password ""
    Then the log in is successfull