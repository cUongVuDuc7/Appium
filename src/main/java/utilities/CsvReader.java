package utilities;

import browserstack.shaded.org.json.JSONArray;
import browserstack.shaded.org.json.JSONObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import helpers.LogHelper;
import helpers.PropertiesFile;
import org.slf4j.Logger;
import java.io.*;
import java.util.List;
import static helpers.PathHelper.projectPath;

public class CsvReader {
    public static final String FILE_PATH_DATA = projectPath + "data" + File.separator + "DataTest.csv";
    public static Logger logger = LogHelper.getLogger();
    public static void initCSVReader() {
        logger.info("init CSV Reader ");
        try {
            FileReader filereader = new FileReader(FILE_PATH_DATA);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
