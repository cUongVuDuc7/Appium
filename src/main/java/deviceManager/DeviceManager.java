package deviceManager;

import driver.DriverManager;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import static appiumServer.AppiumService.*;


public class DeviceManager {
    private static Logger logger = LogHelper.getLogger();
    public static Map<String, String> deviceAndSession = new HashMap<>();
    public static List<String> listIdDevice = new ArrayList<>();

    public static List<String> getIdAndroidSDevices() {
        String listDevice = "";
        List<String> deviceList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1 && "device".equals(parts[1])) {
                        deviceList.add(parts[0]);
                        listDevice += parts[0] + ",";
                    }
                }
                System.out.println(listDevice);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        PropertiesFile.setDataPropValue("LIST_ID_DEVICE", listDevice.substring(0, listDevice.lastIndexOf(",")));
        System.out.println(deviceList);
        return deviceList;
    }
    public static String getDeviceBrs(String[] config){
        String listDevice = "";
        for (int i = 0; i < config.length; i++) {
            listDevice += config[i].substring(config[i].indexOf("=") + 1) + ",";
            i++;
        }
        PropertiesFile.setDataPropValue("LIST_NAME_DEVICE", listDevice.substring(0, listDevice.lastIndexOf(",")));
        return listDevice;
    }
    public static void setListNameDevice(){
        String deviceName = "";
        String[] listId = PropertiesFile.getPropValue("LIST_ID_DEVICE").split(",");
        for (int i = 0; i < listId.length; i++) {
            deviceName += getNameDevice(listId[i]) + ",";
        }
        PropertiesFile.setDataPropValue("LIST_NAME_DEVICE", deviceName.substring(0, deviceName.lastIndexOf(",")));

    }
    public static String getNameDevice(String udid){
        String deviceName = "";
        try {
            String command = String.format("adb -s %s shell getprop %s", udid , "ro.product.brand");
            Process process = Runtime.getRuntime().exec( command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            deviceName = reader.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Device name: " + deviceName);
        return deviceName;
    }
    public static String getVersionDevice(String udid){
        String version = "";
        try {
            String command = String.format("adb -s %s shell getprop %s", udid , "ro.build.version.release");
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader= new BufferedReader(new InputStreamReader(process.getInputStream()));
            version = reader.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Device version: " + version);
        return version;
    }
    public static String getDeviceTesting(){
        System.out.println( "Device và session in device Mng : " + deviceAndSession);
        String device = "";
        Set<String> keyDevice = deviceAndSession.keySet();
        for (String key : keyDevice) {
            if(deviceAndSession.get(key) == null){
                device = key;
                break;
            }
        }
        System.out.println("getDeviceTesting: " + device);
        return device;
    }
    public static void closeApp(String appPackage){
        logger.info("Close app ");
        String app = PropertiesFile.getPropValue(appPackage);
        if (app == null) {
            app = appPackage;
        }
        DriverManager.getDriver().terminateApp(app);
    }
    public static void launchApp(){
        logger.info("Launch app ");
    }
    public static void activeApp(String appPackage){
        logger.info("Active app ");
        String app = PropertiesFile.getPropValue(appPackage);
        if (app == null) {
            app = appPackage;
        }
        DriverManager.getDriver().activateApp(app);
    }
}
