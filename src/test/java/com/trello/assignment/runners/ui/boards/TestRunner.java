package com.trello.assignment.runners.ui.boards;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/trello/ui",    // Path to  feature files
        glue = "com/trello/assignment/stepDefinitions/ui",     // Package containing step definitions
        plugin = {
                "pretty",                               // Prints formatted console output
                "html:target/trello/ui/reports.html",   // Generates HTML report
                "json:target/trello/ui/reports.json"    // Generates JSON report
        },
        tags = "@UITesting"                             // Run All UI Tests
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}
