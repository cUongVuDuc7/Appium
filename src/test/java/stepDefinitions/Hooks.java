package stepDefinitions;

import base.BaseRunner;
import deviceManager.DeviceManager;
import driver.DriverManager;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import java.io.File;
import java.io.IOException;
import static constant.Constant.*;
import static helpers.LogCat.getLog;
import static helpers.PathHelper.projectPath;
import static myListener.MyListener.*;
import static utilities.ScreenRecorderUtil.saveVideo;
import io.cucumber.java.Scenario;
import utilities.TrackerTestResult;

public class Hooks {
    private static Logger logger = LogHelper.getLogger();

    @BeforeAll
    public static void beforeAll() {
        logger.info("_________BEFORE ALL CUCUMBER__________");
        PropertiesFile.setPropertiesFile();
        try {
            if (PropertiesFile.getPropValue("OVER_WRITE_REPORT").equals("YES")) {
                FileUtils.deleteDirectory(new File("target" + File.separator + "allure-results"));
                logger.info("Deleted directory allure-results");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterAll
    public static void afterAll() {
        logger.info(" AFTER ALL CUCUMBER");
        logger.info("totalTCs: " + totalTCs);
    }

    @Before
    public void beforeScenario() {
        logger.info(" Before scenario");
        totalTCs = totalTCs + 1;
        logger.info("start recording screen");
        DriverManager.getDriver().startRecordingScreen();
        attachInform(BaseRunner.information.get());
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("After scenario");
        if (scenario.getStatus().equals(Status.PASSED)) {
            TrackerTestResult.incrementPassed();
        }
        else if (scenario.getStatus().equals(Status.SKIPPED)) {
            TrackerTestResult.incrementSkipped();
        }
        else if (scenario.getStatus().equals(Status.FAILED)) {
            TrackerTestResult.incrementFailed();
            saveScreenshotPNG();
            getLog();
            logDevices(projectPath + "mylog.txt");
            String video = DriverManager.getDriver().stopRecordingScreen();
            String name = saveVideo(video, scenario);
            attachVideoRecord(name);
        }
        DeviceManager.closeApp("APP_PACKAGE");
        DeviceManager.activeApp("APP_PACKAGE");
    }
}
