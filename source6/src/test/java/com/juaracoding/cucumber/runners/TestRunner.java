package com.juaracoding.cucumber.runners;

import com.juaracoding.cucumber.reports.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

/**
 * Enterprise Cucumber TestNG Runner terintegrasi dengan ExtentReports 5 & PageFactory.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.juaracoding.cucumber.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        if (ExtentManager.getInstance() != null) {
            ExtentManager.getInstance().flush();
        }
    }
}

