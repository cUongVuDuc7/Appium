package myListener;


import helpers.LogHelper;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import io.qameta.allure.Allure;
import org.slf4j.Logger;


public class CucumberListener implements EventListener {
    protected Logger logger = LogHelper.getLogger();
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::ScenarioFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);

    }
    private void runStarted(TestRunStarted event) {
    }
    private void runFinished(TestRunFinished event) {

    }
    private void featureRead(TestSourceRead event) {
        String featureSource = event.getUri().toString();
        System.out.println("Feature Source: " + featureSource);
    }
    private void ScenarioStarted(TestCaseStarted event) {
        logger.info("Scenario Started");
        String originalDescription = event.getTestCase().getName();
        System.out.println("originalDescription: " + originalDescription);
    }
    private void ScenarioFinished(TestCaseFinished event) {
        logger.info("Scenario Finished");
    }
    private void stepStarted(TestStepStarted event) {
        logger.info("Step Start");
    }
    private void stepFinished(TestStepFinished event) {
        logger.info("Step Finish");
    }
}
