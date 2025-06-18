package cucumber;

import pages.PageObjectManager;

public class TestContext {
    private ScenarioContext scenarioContext;
    private PageObjectManager pageObjectManager;
    public TestContext(){
        scenarioContext = new ScenarioContext();
        pageObjectManager = new PageObjectManager();
    }
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
