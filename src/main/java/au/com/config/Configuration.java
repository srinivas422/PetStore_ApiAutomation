package au.com.config;

import au.com.util.FileSystemUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Configuration {
    public static final String Properties_file = "config.properties";

    public static Properties properties;

    public static void loadProperties() throws IOException, URISyntaxException {
        properties = new Properties();
        properties.load(new FileInputStream(FileSystemUtil.getResourcePath(Properties_file)));
    }

    public static String getProperty(String propName) throws IOException, URISyntaxException {
        return properties.getProperty(propName);
    }
}
