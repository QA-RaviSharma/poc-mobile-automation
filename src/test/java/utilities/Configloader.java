package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configloader {
    //Logger Setup
    public static Logger log = LoggerFactory.getLogger(Configloader.class);

    //Setup properties file
    public static Properties property() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("/Users/ravis/Documents/Ravi Kumar Sharma QA/Successive.Tech/MarketCube Project/New API Automation Repo/mobile.automation.experiments/src/test/resources/config/configuration.properties");
            properties.load(fileInputStream);
            return properties;
        } catch (Exception e) {
            log.error("Exception occurred property!!!");
            e.printStackTrace();
        }
        return null;
    }

    //Setup native apps installing
    public static String getCustomAppPath(String appName) {
        File directoryPath = new File("src/test/resource/apps");
        File path = new File(directoryPath, appName);
        log.info("APK Data: {}", path);
        return path.getAbsolutePath();
    }
}
