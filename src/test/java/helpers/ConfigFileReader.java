package helpers;

import java.util.Properties;

public class ConfigFileReader {
    public static String getReportConfigPath() {
        String reportConfigPath = new Properties().getProperty("reportConfigPath");
        if (reportConfigPath != null) return reportConfigPath;
        else
            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
