@UITesting @E2ETesting
Feature: New Trello Board Creation

  Background:
    Given I have logged into Trello

  @SmokeTest
  Scenario Outline: Create a new Trello board
    When I create a new Trello Board
    And I enter "<BoardTitle>" as the board title
    And I click the Create button from menu
    Then the new board "<BoardTitle>" should be displayed in the board page
    Examples:
      | BoardTitle  |
      | My New Board |

  Scenario: Creating a new Trello board is not possible without Board Title
    When I create a new Trello Board
    And I enter "" as the board title
    Then the create button should not be enabled