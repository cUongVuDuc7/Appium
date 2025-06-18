package utilities;

import browserstack.shaded.org.json.JSONArray;
import browserstack.shaded.org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helpers.LogHelper;
import helpers.PropertiesFile;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static helpers.PathHelper.projectPath;

public class JsonReader {
    public static final String FILE_PATH_RESULT_TEST = projectPath + "TestResult.json";
    public static Logger logger = LogHelper.getLogger();
    public static void initTestResultJSON() {
        logger.info("init test result json");
        String[] listDevice = PropertiesFile.getPropValue("LIST_NAME_DEVICE").split(",");
        JSONArray testResults = new JSONArray();
        for (int i = 0; i < Integer.valueOf(PropertiesFile.getPropValue("NUMBER_DEVICE")); i++) {
            JSONObject device = new JSONObject();
            device.put("deviceName", listDevice[i]);
            device.put("pass", 0);
            device.put("fail", 0);
            device.put("skip", 0);
            testResults.put(device);
        }
        try (FileWriter file = new FileWriter(FILE_PATH_RESULT_TEST)) {
            file.write(testResults.toString());
            logger.info("Test results saved to testResults.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateTestResultJSON(String deviceName, int pass, int fail, int skip) {
        logger.info("update result json of: " + deviceName + " " + pass + " " + fail + " " + skip);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(FILE_PATH_RESULT_TEST);
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            if (rootNode.isArray()) {
                for (JsonNode deviceNode : rootNode) {
                    if (deviceNode.get("deviceName").asText().equals(deviceName)) {
                        int passValue = (deviceNode).get("pass").intValue();
                        int failValue = (deviceNode).get("fail").intValue();
                        int skipValue = (deviceNode).get("skip").intValue();
                        ((ObjectNode) deviceNode).put("pass", pass + passValue);
                        ((ObjectNode) deviceNode).put("fail", fail + failValue);
                        ((ObjectNode) deviceNode).put("skip", skip + skipValue);
                    }
                }
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, rootNode);
            logger.info("Updated JSON file successfully!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
