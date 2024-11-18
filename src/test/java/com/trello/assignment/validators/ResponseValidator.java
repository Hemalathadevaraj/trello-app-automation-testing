package com.trello.assignment.validators;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Validates responses from API
 */
public class ResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        response.then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON);
    }
}
