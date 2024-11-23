package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    private static final String propertyfile = "src/config/db.properties";  // Path to your db.properties file

    // Method to load database properties from the db.properties file
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(propertyfile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
