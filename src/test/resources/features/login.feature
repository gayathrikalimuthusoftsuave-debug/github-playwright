Feature: Login Test

  Scenario Outline: Login validation
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    Then login should be "<result>"

    Examples:
      | username | password      | result  |
      | student  | wrong123      | fail    |
      | student  | Password123   | success |