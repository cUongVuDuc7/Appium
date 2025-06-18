package runners;
import base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/MatchSchedule.feature",
        glue = {
                "stepDefinitions"
        },
        plugin = {
                "summary",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        }
        ,tags = "@LiveScore-2"
//        monochrome = true,
//        dryRun = true
)
public class MatchScheduleRunner extends BaseRunner {
}