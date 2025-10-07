Feature: User Sign Up
  As a new user
  I want to fill out the sign up form
  So that I can create an account
  Background:
    Given I am on the sign up page
  Scenario: Successfully fill all sign up fields
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter "john.doe@gmail.com" as email
    And I enter "SecurePass123!" as password
    And I enter "SecurePass123!" as confirm password
    And I enter "1234567890" as student phone number
    And I enter "0987654321" as parent phone number
    And I select "2" as student academic level
    Then all fields should be filled correctly
    Then the user presses signup button
#    Then a Success Message should be displayed

  Scenario Outline: Fill sign up form with different data
    When I enter "<firstName>" as first name
    And I enter "<lastName>" as last name
    And I enter "<email>" as email
    And I enter "<password>" as password
    And I enter "<confirmPassword>" as confirm password
    And I enter "<studentPhone>" as student phone number
    And I enter "<parentPhone>" as parent phone number
    And I select "<academicLevel>" as student academic level
    Then all fields should be filled correctly

    Examples:
      | firstName | lastName | email                  | password       | confirmPassword | studentPhone | parentPhone | academicLevel |
      | Alice     | Smith    | alice.smith@gmail.com  | Test@1234      | Test@1234       | 01000627666   | 01000627669  | 1             |
      | Bob       | Johnson  | bob.johnson@gmail.com  | MyPass@567     | MyPass@567      | 01000627666  | 01000627669  | 3             |
      | Carol     | Williams | carol.w@gmail.com      | Secure#890     | Secure#890      | 01000627666   | 01000627669 | 2             |