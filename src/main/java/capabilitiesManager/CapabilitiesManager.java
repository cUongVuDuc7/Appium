package capabilitiesManager;

import helpers.LogHelper;
import helpers.PathHelper;
import helpers.PropertiesFile;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import static helpers.PathHelper.projectPath;
import static keyword.KeywordWeb.rd;

public class CapabilitiesManager {
    public static String appName = PathHelper.getFileName("app");
    public static String appPath  = projectPath + "app" + File.separator;
    private static String userName = System.getenv("BROWSERSTACK_USERNAME");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static String app = System.getenv("BROWSERSTACK_APP");

    public static Logger logger = LogHelper.getLogger();
    public DesiredCapabilities getCaps(String cloudPlatform, String deviceName, String deviceVersion, String deviceId) {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("version", deviceVersion);
        if (cloudPlatform.equals("browserStack")){
            dc.setCapability("deviceName", deviceName);
            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("userName", userName);
            browserstackOptions.put("accessKey", accessKey);
            dc.setCapability("app", app);
            dc.setCapability("bstack:options", browserstackOptions);
        }
        else {
            System.out.println("path " + appPath + appName);
            dc.setCapability("udid", deviceId);
            dc.setCapability("noReset", true);
            dc.setCapability("autoGrantPermissions", true);
            dc.setCapability("automationName", "UiAutomator2");
//            dc.setCapability("appPackage", "vn.vtvlive.ontv.a.dev");
//            dc.setCapability("appActivity", "vn.vtvlive.ontv.a.feature.main.activity.MainActivity");
            dc.setCapability("app",  appPath + appName);
        }
        System.out.println("capability" + dc);
        return dc;
    }
}
