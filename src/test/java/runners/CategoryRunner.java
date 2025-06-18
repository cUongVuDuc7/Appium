package runners;

import base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Category.feature",
        glue = {
                "stepDefinitions"
        },
        plugin = {
                "summary",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
        ,tags = "@Cat_112 or @Cat_113"
)
public class CategoryRunner extends BaseRunner {

}
