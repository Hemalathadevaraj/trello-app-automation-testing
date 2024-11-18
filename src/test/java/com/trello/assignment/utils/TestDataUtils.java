package com.trello.assignment.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import com.trello.api.generated.Board;
import org.openapitools.jackson.nullable.JsonNullableModule;

import java.util.Map;

/**
 * Util class helps to create Test Data required for Step definitions
 */
public class TestDataUtils {

    public static Board mapDataTableToBoard(DataTable dataTable) {
        Map<String, String> boardDetailsMap = dataTable.asMap();
        Board board = new Board();
        board.name(boardDetailsMap.get("name"));
        board.desc(boardDetailsMap.get("desc"));
        return board;
    }

    public static ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JsonNullableModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        return objectMapper;
    }
}
