## Trello application automation

## Introduction
Trello application automation project runs Automated Test cases using Cucumber BDD with Selenium for UI and RestAssured for API

## System Requirements
- Java 17 
- Maven 3.x.x
- Installed latest browsers versions of chrome, firefox or edge

## Pre-requisites & Assumptions on this assignment
- Trello Board user is an existing user
- Api-Key and Authorisation Token from Trello PowerUps
- Executing UI and API Test cases separately
- Using Selenium for UI and RestAssured for API automation, to show diverse knowledge on UI and API testing

## Tools and Technologies
- Java 17
- BDD: Cucumber
- API Automation: RestAssured (5.5.0)
- UI Automation Tool: Selenium Webdriver (4.25.0)
- Build Tool: Maven (3.x.x)

## Installation
- Clone the repository
      ```
          git clone https://github.com/Hemalathadevaraj/trello-app-automation-testing.git
          cd trello-app-automation-testing
      ```
- Execute ```mvn clean test```
- Report are generated in ${project-root}/target/trello/<api/ui>

Note: you can change the browser in [config.properties](src/test/resources/config/config.properties) for UI testing

## Framework 

### API Framework
![API Automation Framework.jpg](API%20Automation%20Framework.jpg)

### UI Framework
![UI Automation Framework.jpg](UI%20Automation%20Framework.jpg)

## Future Improvements
- Playwright for both UI and API Automation
- Integrating with CI/CD pipeline using Jenkins or other cloud services
- Use of Reportportal.io for advanced reporting capabilities
- Reading credentials and secrets from Azure Key Vault, CyberArk, etc instead of config properties
- Using TestNG for Parallel execution on all browsers

## How to scale/update this framework

- Add feature file under resources folder with a naming convention of <feature>-<api/ui> for eg: boards-api.feature, boards-ui.feature
- Implement the Step Definitions of the feature file in location src/test/java/com/trello/assignment/stepDefinitions/<api/ui>/<Feature><API/UI>StepDefinition.java
- Create a TestRunner class in location src/test/java/com/trello/assignment/runners/<api/ui>/<feature>/TestRunner.java

### API Framework
- Create API Actions under class src/test/java/com/trello/assignment/apiActions and implement all API interactions
- Add required properties to [config.properties](src/test/resources/config/config.properties) for API Testing

### UI Framework
- Page Object model implementation used in StepDefinition class under src/main/java/com/trello/assignment/pages package
- Using [BrowserHook.java](src/main/java/com/trello/assignment/browser/BrowserHook.java) you can interact with the WebDriver for browser actions
  - Note: Declare new interaction methods if missing in BrowserHook and implement in BrowserHookImpl 
- Add required properties to [config.properties](src/test/resources/config/config.properties) for UI Testing

## Project Structure
- **src/main/java: Contains Page Object model classes, UI Automation Tool implementations relevant for UI testing
- **src/main/resources: Contains openapi.json
- **src/test/java: Contains API Actions, API Utilities and Test Runners, Step Definitions for both UI and API
- **src/test/resources: Contains config properties and feature files