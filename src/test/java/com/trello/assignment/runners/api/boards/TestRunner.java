package com.trello.assignment.runners.api.boards;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/trello/api",
        glue = "com/trello/assignment/stepDefinitions/api",
        plugin = {"pretty", "html:target/trello/api/report.html", "json:target/trello/api/reports.json"},
        monochrome = true,
        tags = "@APITesting"
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
