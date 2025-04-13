package runners;
import base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ForgotPassword.feature",
        glue = {
                "stepDefinitions"
        },
        plugin = {
                "summary",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
//        ,tags = "@FP_41 or @FP_42"
)
public class ForgotPasswordRunner extends BaseRunner {
}

