package runners;
import base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
//                "myListener.CucumberListener"
        },
        features = "src/test/resources/features/Profile.feature",
        glue = {
                "stepDefinitions"
        }
        ,tags = "@Profile-55-56-57"
)
public class ProfileRunner extends BaseRunner {
}


