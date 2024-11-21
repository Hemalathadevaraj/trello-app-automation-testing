package com.trello.assignment.stepDefinitions.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trello.assignment.models.Board;
import com.trello.assignment.apiActions.BoardApiActions;
import com.trello.assignment.utils.TestDataUtils;
import com.trello.assignment.utils.ConfigProperties;
import com.trello.assignment.utils.RestAssuredUtils;
import com.trello.assignment.validators.ResponseValidator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class TrelloBoardAPIStepDefinition {

    private final ObjectMapper objectMapper;
    private final Map<String, String> testContext;

    private Response response;

    public TrelloBoardAPIStepDefinition() {

        ConfigProperties.initProperties();
        objectMapper = TestDataUtils.getObjectMapper();
        testContext = new HashMap<>();
    }

    @Given("I create a Trello board using input")
    public void createTrelloBoard(DataTable boardDetails) throws JsonProcessingException {
        Board board = TestDataUtils.mapDataTableToBoard(boardDetails);
        String requestBody = objectMapper.writeValueAsString(board);
        response = BoardApiActions.createBoard(requestBody);
        testContext.put("boardId", RestAssuredUtils.getValueFromResponse(response, "id"));
    }

    @When("I send GET request to retrieve the Trello Board")
    public void retrieveTrelloBoard() {
        String boardId = testContext.get("boardId");
        response = BoardApiActions.getBoardDetails(boardId);
    }

    @When("I send PUT request to update the board")
    public void updateTrelloBoard(DataTable boardDetails) throws JsonProcessingException {
        String boardId = testContext.get("boardId");
        Board board = TestDataUtils.mapDataTableToBoard(boardDetails);
        board.setId(boardId);
        String requestBody = objectMapper.writeValueAsString(board);
        response = BoardApiActions.updateBoard(boardId, requestBody);
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to update board name");
    }

    @And("I verify the board name is updated to {string}")
    public void verifyBoardName(String expectedName) {
        String boardId = testContext.get("boardId");
        response = BoardApiActions.getBoardDetails(boardId);
        Assert.assertEquals(response.jsonPath().getString("name"), expectedName, "Board name not updated");
    }

    @And("I delete the board")
    public void deleteTrelloBoard() {
        String boardId = testContext.get("boardId");
        response = BoardApiActions.deleteBoard(boardId);
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to delete board");
    }

    @And("I verify the board no longer exists")
    public void verifyBoardDeleted() {
        String boardId = testContext.get("boardId");
        response = BoardApiActions.getBoardDetails(boardId);
        Assert.assertEquals(response.getStatusCode(), 404, "Board still exists");
    }

    @Then("I should receive a {int} status code")
    public void verifyStatusCode(int expectedStatusCode) {

        ResponseValidator.validateStatusCode(response, expectedStatusCode);
    }

    @And("the response should contain the board details:")
    public void validateTrelloBoardDetailsFromResponse(DataTable expectedBoardDetails) throws JsonProcessingException {
        Map<String, String> expectedBoardDetailsMap = expectedBoardDetails.asMap();
        Board boardFromResponse = objectMapper.readValue(response.asString(), Board.class);
        Assert.assertNotNull(boardFromResponse.getId());
        Assert.assertEquals(boardFromResponse.getName(), expectedBoardDetailsMap.get("name"));
        Assert.assertEquals(boardFromResponse.getDesc(), expectedBoardDetailsMap.get("desc"));
    }
}