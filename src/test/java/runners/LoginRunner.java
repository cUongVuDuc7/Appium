package runners;
import base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = {
                "stepDefinitions"
        },
        plugin = {
                "summary",
//                "html:target/cucumber-reports/TestRunnerCategoryCMS.html",
//                "json:target/cucumber-reports/TestRunnerCategoryCMS.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
        ,tags = "@Login_1"
)
public class LoginRunner extends BaseRunner {
}
