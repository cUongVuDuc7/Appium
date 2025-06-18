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
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
//        ,tags = "@Login_3_4"
)
public class HomeRunner extends BaseRunner {

}
