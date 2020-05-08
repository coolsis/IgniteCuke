package com.ignite.step_defs;

import com.ignite.utilities.BrowserUtils;
import com.ignite.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static com.ignite.utilities.BrowserUtils.waitFor;

public class Hook {

    private static Logger log = Logger.getLogger(Hook.class);

    @Before
    public void setUp() {
        Driver.getDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        waitFor(3);
       // Driver.closeDriver();
    }

}
