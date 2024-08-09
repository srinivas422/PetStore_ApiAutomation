package au.com.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileSystemUtil {
    public static String getResourcePath(String path) throws URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        return Paths.get(resource.toURI()).toString();
    }
}
