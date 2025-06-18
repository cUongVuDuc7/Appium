package driver;


import helpers.LogHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;

public class DriverManager {
    private static Logger logger = LogHelper.getLogger();
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    public static AndroidDriver getDriver() {
        return driver.get();
    }
    public static void setDriver(AndroidDriver driver) {
        logger.info("Set driver");
        DriverManager.driver.set(driver);
    }
    public static void quit() {
        logger.info("Quit driver");
        DriverManager.driver.get().quit();
    }
    public static SessionId getSession() {
        SessionId session = driver.get().getSessionId();
        logger.info("get session: " + session);
        return session;
    }
}

