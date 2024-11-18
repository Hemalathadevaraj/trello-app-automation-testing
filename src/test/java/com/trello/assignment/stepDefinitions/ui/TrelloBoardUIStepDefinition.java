package com.trello.assignment.stepDefinitions.ui;

import com.trello.assignment.apiActions.BoardApiActions;
import com.trello.assignment.pages.BoardPage;
import com.trello.assignment.pages.HomePage;
import com.trello.assignment.pages.LoginPage;
import com.trello.assignment.browser.BrowserHook;
import com.trello.assignment.browser.selenium.BrowserHookImpl;
import com.trello.assignment.utils.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.time.LocalDateTime;

public class TrelloBoardUIStepDefinition {

    private BrowserHook browserHook;

    private LoginPage loginPage;
    private HomePage homePage;
    private BoardPage boardPage;

    @Before
    public void getBrowserDriver() {
        ConfigProperties.initProperties();
        String browserName = ConfigProperties.getPropertyValue("browser");
        browserHook = new BrowserHookImpl(browserName);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = browserHook.takeScreenShot();
            scenario.attach(screenshot, "image/png", scenario.getName() + "_FAILED_" + LocalDateTime.now());
        } else {
            byte[] screenshot = browserHook.takeScreenShot();
            scenario.attach(screenshot, "image/png", scenario.getName() + '_' + LocalDateTime.now());
        }
        browserHook.closeDriver();
        BoardApiActions.deleteAllBoards();
    }

    @Given("I have logged into Trello")
    public void loginToTrello() {
        loginPage = new LoginPage(browserHook);
        loginPage.openUrl();
        loginPage.clickLoginButton();
        loginPage.enterEmailID();
        loginPage.clickContinueButton();
        loginPage.enterPassword();
        loginPage.clickSubmitButton();
    }
    
    @When("I create a new Trello Board")
    public void createTrelloBoard() {
        homePage = new HomePage(browserHook);
        homePage.clickCreateButton();
        homePage.clickOnCreateBoardOnMenu();
    }

    @When("I enter {string} as the board title")
    public void enterTrelloBoardTitle(String boardName) {

        homePage.fillBoardTitle(boardName);
    }

    @When("I click the Create button from menu")
    public void clickCreateButtonOnMenu() {

        homePage.clickOnCreateButtonFromMenu();
    }

    @Then("the new board {string} should be displayed in the board page")
    public void validateBoardIsCreated(String boardName) {
        boardPage = new BoardPage(browserHook);
        Assert.assertEquals(boardPage.getBoardTitle(), boardName);
    }

    @Then("the create button should not be enabled")
    public void validateCreateButtonIsNotEnabled() {
        Assert.assertFalse(homePage.isCreateBoardButtonEnabled());

        // making a scenario fail for demo
        // Assert.assertTrue(homePage.isCreateBoardButtonEnabled());
    }
}