@APITesting @E2ETesting
Feature: Trello Board Management

  @SmokeTest
  Scenario: Create Board and validate the response details
    Given I create a Trello board using input
      | name | Test Board                  |
      | desc | This is a test trello board |
    Then I should receive a 200 status code
    And the response should contain the board details:
      | name | Test Board                  |
      | desc | This is a test trello board |
    And I delete the board
    And I verify the board no longer exists

  Scenario: Get board details
    Given I create a Trello board using input
      | name | Test Board                  |
      | desc | This is a test trello board |
    When I send GET request to retrieve the Trello Board
    Then I should receive a 200 status code
    And the response should contain the board details:
      | name | Test Board                  |
      | desc | This is a test trello board |
    And I delete the board
    And I verify the board no longer exists

  Scenario Outline: Update board details
    Given I create a Trello board using input
      | name | <OldBoardName>              |
      | desc | This is a test trello board |
    When I send PUT request to update the board
      | name | <NewBoardName> |
    Then I should receive a 200 status code
    And I verify the board name is updated to "<NewBoardName>"
    And I delete the board
    Examples:
      | NewBoardName     | OldBoardName     |
      | My Updated Board | My Created board |

  Scenario: Create a Board with Invalid Data
    Given I create a Trello board using input
      | name |  |
    Then I should receive a 400 status code