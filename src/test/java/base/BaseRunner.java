package base;

import appiumServer.AppiumService;
import capabilitiesManager.CapabilitiesManager;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utilities.JsonReader;
import utilities.TrackerTestResult;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static deviceManager.DeviceManager.*;
import static utilities.JsonReader.*;
import static utilities.TrackerTestResult.*;

import mySQL.PostGre;
public class BaseRunner {
    public static AndroidDriver driver;
    public AppiumService appiumService ;
    public CapabilitiesManager capabilitiesManager;
    public JsonReader jsonReader;
    public static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();
    public static final ThreadLocal<Map<String, String>> information = new ThreadLocal<>();
    public static TestNGCucumberRunner getRunner() {
        return testNGCucumberRunner.get();
    }

    public BaseRunner() {
        appiumService = new AppiumService();
        capabilitiesManager = new CapabilitiesManager();
        jsonReader = new JsonReader();
    }
    public static void setRunner(TestNGCucumberRunner testNGCucumber) {
        testNGCucumberRunner.set(testNGCucumber);
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platform","deviceName","deviceVersion","deviceId"})
    public void setUpDeviceAndServer(String platform, String deviceName,String deviceVersion,String deviceId) throws Exception {
        appiumService.startServer();
        DesiredCapabilities cap = capabilitiesManager.getCaps(platform, deviceName, deviceVersion, deviceId);
        if(platform.equals("browserStack")) {
            driver = new AndroidDriver(new URL("http://hub.browserstack.com/wd/hub"), cap);
        }
        else {
            driver = new AndroidDriver(appiumService.getUrlServer(), cap);
        }
        DriverManager.setDriver(driver);
        deviceAndSession.put(deviceId, String.valueOf(DriverManager.getSession()));
        setRunner(new TestNGCucumberRunner(this.getClass()));
        setInformAllure(deviceName, deviceVersion, deviceId);
        setSkipped();
        setPassedTCs();
        setFailed();
    }
    @Test(dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        getRunner().runScenario(pickle.getPickle());
    }
    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterTest(alwaysRun = true)
    @Parameters({"deviceName","deviceId"})
    public void afterTest(String deviceName, String deviceId){
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
        jsonReader.updateTestResultJSON(deviceName, TrackerTestResult.getPassed(), TrackerTestResult.getFailed(), TrackerTestResult.getSkipped());
        deviceAndSession.put(deviceId, null);
        appiumService.stopServer();
        if (testNGCucumberRunner != null) {
            getRunner().finish();
        }
    }
    public void setInformAllure(String deviceName,String deviceVersion,String deviceId) {
        Map<String, String> inFrom = new HashMap<>();
        inFrom.put("deviceId", deviceId);
        inFrom.put("deviceName", deviceName);
        inFrom.put("deviceVersion", deviceVersion);
        information.set(inFrom);
    }
}