package com.acme.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"classpath:features"}, glue = {"com.acme.cucumber.step"})
public class FundTransferRunnerTest {
}
