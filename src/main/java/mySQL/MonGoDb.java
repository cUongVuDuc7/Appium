package mySQL;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import static com.mongodb.client.model.Sorts.descending;

public class MonGoDb {
    public static Logger logger = LogHelper.getLogger();
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    @Step("Kết nốt mongodb : {0}")
    public MongoClient connectMonGoDb(String url) {
        logger.info("Connect mongo DB: ");
        url = PropertiesFile.getPropValue(url);
        this.mongoClient = new MongoClient(new MongoClientURI(url));
        return this.mongoClient;
    }
    @Step("Get data mongodb : {0}")
    public MongoDatabase getDatabase(String dataBase){
        logger.info("Get db mongo: ");
        String content= PropertiesFile.getPropValue(dataBase);
        if (content == null) {
            content = dataBase;
        }
        this.database = this.mongoClient.getDatabase(content);
        return this.database;
    }
    @Step("Get table mongodb : {0}")
    public MongoCollection getTable(String tableName){
        logger.info("Get table db mongo: ");
        this.collection = this.database.getCollection(tableName);
        return this.collection;
    }
    @Step("Get data device : {0}")
    public String getResultDevice(Bson filter){
        logger.info("Get result device mongo: ");
        Document lastDocument = this.collection.find(filter).sort(descending("created_at")).first();
        return lastDocument.get("device_name").toString();
    }

}
