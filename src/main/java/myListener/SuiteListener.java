package myListener;

import helpers.LogHelper;
import helpers.PropertiesFile;
import org.slf4j.Logger;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import java.util.List;
import static deviceManager.DeviceManager.*;
import static helpers.PathHelper.projectPath;
import static helpers.ReadYaml.loadConfig;
import static utilities.JsonReader.*;

public class SuiteListener implements IAlterSuiteListener {
    private static final Logger logger = LogHelper.getLogger();
    @Override
    public void alter(List<XmlSuite>  suites) {
        logger.info("before all suites");
        PropertiesFile.setPropertiesFile();
        XmlSuite suite = suites.get(0);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        if (suite.getParameter("platform").equals("android device")) {
            int numberDevice = getIdAndroidSDevices().size();
            PropertiesFile.setDataPropValue("NUMBER_DEVICE", String.valueOf(numberDevice));
            PropertiesFile.setDataPropValue("NUMBER_TEST_IN_SUITE", String.valueOf(suite.getTests().size()));
            suite.setThreadCount(numberDevice);
            setListNameDevice();
        }
        else {
            String[] config = loadConfig(projectPath + "browserstack.yml");
            suite.setThreadCount(config.length/2);
            PropertiesFile.setDataPropValue("NUMBER_TEST_IN_SUITE", String.valueOf(config.length/2));
            PropertiesFile.setDataPropValue("NUMBER_DEVICE", String.valueOf(config.length/2));
            int count = 0;
            for (int i = 0; i < suite.getTests().size(); i++) {
                suite.getTests().get(i).addParameter("deviceName",config[count]);
                suite.getTests().get(i).addParameter("deviceVersion",config[count + 1]);
                count += 2;
                if(count == config.length - 1) {
                    count = 0;
                }
            }
            getDeviceBrs(config);
        }
        initTestResultJSON();
    }
}

