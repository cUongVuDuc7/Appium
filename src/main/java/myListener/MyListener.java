package myListener;

import driver.DriverManager;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static deviceManager.DeviceManager.*;
import static helpers.LogCat.removeLog;
import static utilities.ScreenRecorderUtil.VIDEO_FILES_FOLDER;

public class MyListener implements ITestListener {
    private static final Logger logger = LogHelper.getLogger();

    @Attachment(type = "text/plain")
    public static byte[] logDevices(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return new byte[0];
        }
    }
    public static void attachInform(Map<String, String> inform) {
        String tsvContent = "Device Id:\t\t" + inform.get("deviceId") + "\n"
                + "Device Name:\t\t" + inform.get("deviceName") + "\n"
                + "Device Version:\t\t"+ inform.get("deviceVersion") + "\n";
        Allure.addAttachment("InForm of device", "text/tab-separated-values", tsvContent, ".tsv");
    }
    public static void attachChart() {
        String chartHTML = "<canvas id='myChart' width='400' height='200'></canvas>"
                + "</script>";
        Allure.addAttachment("Total", "text/html", chartHTML, ".html");
    }
    @Attachment(value = "Video record", type = "video/mp4")
    public static byte[] attachVideoRecord(String fileName) {
        Path path = Paths.get(VIDEO_FILES_FOLDER + fileName);
        System.out.println("Path video: " + path);
        try (InputStream is = Files.newInputStream(path)) {
            return is.readAllBytes();
        }
        catch (IOException e) {
            return new byte[0];
        }
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }
    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }
    @Override
    public void onStart(ITestContext context) {
        String idDevice = "";
        if (listIdDevice.size() == 1) {
            idDevice = listIdDevice.get(0);
        }
        else {
            if (context.getCurrentXmlTest().getIndex() < Integer.parseInt(PropertiesFile.getPropValue("NUMBER_DEVICE"))) {
                idDevice = PropertiesFile.getPropValue("LIST_ID_DEVICE").split(",")[context.getCurrentXmlTest().getIndex()];
            }
            else {
                idDevice = getDeviceTesting();
            }
        }
        String nameDevice = getNameDevice(idDevice);
        String versionDevice = getVersionDevice(idDevice);
        context.getCurrentXmlTest().addParameter("deviceId", idDevice);
        context.getCurrentXmlTest().addParameter("deviceName", nameDevice);
        context.getCurrentXmlTest().addParameter("deviceVersion", versionDevice);
    }
    @Override
    public void onFinish(ITestContext result) {
        logger.info("End testing " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Running test case " + result.getName());
        removeLog();
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test case " + result.getName() + " is passed.");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test case " + result.getName() + " is failed.");
        logger.error(result.getThrowable().toString());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error("Test case " + result.getName() + " is skipped.");
        logger.error(result.getThrowable().toString());
    }
}

