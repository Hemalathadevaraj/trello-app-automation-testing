package com.trello.assignment.apiActions;

import com.trello.assignment.utils.ConfigProperties;
import com.trello.assignment.utils.RestAssuredUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trello.assignment.apiActions.TrelloAPIConstants.AUTH_KEY;
import static com.trello.assignment.apiActions.TrelloAPIConstants.AUTH_TOKEN;
import static com.trello.assignment.apiActions.TrelloAPIConstants.BOARDS_ENDPOINT;
import static com.trello.assignment.apiActions.TrelloAPIConstants.TRELLO_BASE_URL;

/**
 * Trello Boards specific API actions class
 */
public class BoardApiActions {

    // Create a new board
    public static Response createBoard(String requestBody) {
        String baseUrl = ConfigProperties.getPropertyValue(TRELLO_BASE_URL);
        RequestSpecification requestSpec = RestAssuredUtils.buildRequestSpecification(baseUrl,
                null, BoardApiActions.getDefaultQueryParams(), requestBody);

        return RestAssuredUtils.sendPostRequest(requestSpec, BOARDS_ENDPOINT);
    }

    // Get board details
    public static Response getBoardDetails(String boardId) {
        String baseUrl = ConfigProperties.getPropertyValue(TRELLO_BASE_URL);
        RequestSpecification requestSpec = RestAssuredUtils.buildRequestSpecification(baseUrl,
                null, BoardApiActions.getDefaultQueryParams(), null);

        return RestAssuredUtils.sendGetRequest(requestSpec, BOARDS_ENDPOINT + "/" + boardId);
    }

    // Update board
    public static Response updateBoard(String boardId, String requestBody) {
        String baseUrl = ConfigProperties.getPropertyValue(TRELLO_BASE_URL);
        RequestSpecification requestSpec = RestAssuredUtils.buildRequestSpecification(baseUrl,
                null, BoardApiActions.getDefaultQueryParams(), requestBody);
        return RestAssuredUtils.sendPutRequest(requestSpec, BOARDS_ENDPOINT + "/" + boardId);
    }

    // Delete board
    public static Response deleteBoard(String boardId) {
        String baseUrl = ConfigProperties.getPropertyValue(TRELLO_BASE_URL);
        RequestSpecification requestSpec = RestAssuredUtils.buildRequestSpecification(baseUrl,
                null, BoardApiActions.getDefaultQueryParams(), null);

        return RestAssuredUtils.sendDeleteRequest(requestSpec, BOARDS_ENDPOINT + "/" + boardId);
    }

    // Get All boards assigned to me
    public static Response getAllBoards() {
        String baseUrl = ConfigProperties.getPropertyValue(TRELLO_BASE_URL);
        RequestSpecification requestSpec = RestAssuredUtils.buildRequestSpecification(baseUrl,
                null, BoardApiActions.getDefaultQueryParams(), null);
        return RestAssuredUtils.sendGetRequest(requestSpec, "/member/me" + BOARDS_ENDPOINT);
    }

    // Get All boards and delete
    public static void deleteAllBoards() {
        Response response = getAllBoards();
        List<String> boardIds = response.jsonPath().getList("id", String.class);
        for (String boardId : boardIds) {
            deleteBoard(boardId);
        }
    }

    public static Map<String, String> getDefaultQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("key", ConfigProperties.getPropertyValue(AUTH_KEY));
        queryParams.put("token", ConfigProperties.getPropertyValue(AUTH_TOKEN));
        return queryParams;
    }
}
