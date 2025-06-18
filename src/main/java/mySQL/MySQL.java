package mySQL;

import helpers.LogHelper;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import keyword.KeywordWeb;
import org.slf4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MySQL {
    public static Logger logger = LogHelper.getLogger();
    private HashMap<String, String> dataMap = new HashMap<>();
    private KeywordWeb keyword;
    private Connection con;
    public MySQL() {
        keyword = new KeywordWeb();
    }
    @Step("Kết nốt data base : {0}")
    public Connection setUpDB(String url, String user, String passWord) {
        logger.info("Set Up DB " + url );
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = PropertiesFile.getPropValue(url);
            String dbUser = PropertiesFile.getPropValue(user);
            String dbPass = PropertiesFile.getPropValue(passWord);
            this.con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return this.con;
    }
    @Step("Kết nối db tvplay-upload")
    public void setUpConnectTvPlayUpload(){
        setUpDB("MY_SQL_TV_PLAY_UPLOAD","TV_PLAY_UPLOAD_USER","TV_PLAY_UPLOAD_PASSWORD");
    }
    @Step("Thực hiện truy vấn dữ liệu : {0}")
    public ResultSet queryDb(String query) {
        logger.info("Query DB: " + query);
        String content = PropertiesFile.getPropValue(query);
        if (content == null) {
            content = query;
        }
        try {
            if (this.con == null) {
                throw new IllegalStateException("Database connection is not set up.");
            }
            PreparedStatement stmt = this.con.prepareStatement(content);
            return stmt.executeQuery();
        }
        catch (SQLException e) {
            throw new RuntimeException("Query execution error", e);
        }
    }
    @Step("Lấy dữ liệu từ các cột db")
    public HashMap<String, String> getResultDataBase(ResultSet res) {
        HashMap<String, String> dataMap = new HashMap<>();
        try {
            logger.info(" ResultSetMetaData DB: ");
            ResultSetMetaData md = res.getMetaData();
            while (res.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dataMap.put(md.getColumnName(i), res.getString(i));
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        logger.info("Get result DB:");
        Set<String> set = dataMap.keySet();
        for (String key : set) {
            System.out.println("Key: " + key + "   Value: " + dataMap.get(key));
        }
        return dataMap;
    }
    @Step("Lấy dữ liệu từ các cột db")
    public Map<Integer, Map<String, Object>> getResultDbThan1Rows(ResultSet res) {
        HashMap<Integer, Map<String, Object>> multipleDataMap = new HashMap<>();
        try {
            logger.info(" ResultSetMetaData DB: ");
            ResultSetMetaData md = res.getMetaData();
            int rowIndex = 0;
            while (res.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    rowMap.put(md.getColumnName(i) , res.getString(i));
                }
                multipleDataMap.put(rowIndex++, rowMap);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        logger.info("Get result DB:");
        multipleDataMap.forEach((rowNumber, rowData) -> {
            System.out.println("Row " + rowNumber + ": " + rowData);
        });
        return multipleDataMap;
    }
    @Step("Kiểm tra dữ liệu cột: {0}")
    public void checkDataBase(HashMap<String, String> dataMap, String actuals, String expects) {
        logger.info("Check DB: ");
        String[] actual = actuals.split(",");
        String[] expect = expects.split(",");
        for (int i = 0; i < expect.length; i++) {
            keyword.assertEqualData(dataMap.get(actual[i]), expect[i]);
        }
    }
}
