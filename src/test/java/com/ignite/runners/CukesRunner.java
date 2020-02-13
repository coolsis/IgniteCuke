package com.ignite.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:test-output/ignite-report",
                "json:test-output/cucumber.json",
                "rerun:test-output/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "com/ignite/step_defs",
        tags = "@eifB-i",
        //strict = true,
        dryRun = false

)


public class CukesRunner {

}