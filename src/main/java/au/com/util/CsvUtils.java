package au.com.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CsvUtils {

    public static List<String> readCSV(String path) throws IOException, CsvException, URISyntaxException {
        String resourcePath = FileSystemUtil.getResourcePath(path);
        CSVReader reader = new CSVReader(new FileReader(resourcePath));
        List allRows = reader.readAll();
        return allRows;
    }


    public static String getCSVCellValue(String path, int columnNumber) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(path));
        String[] rowVal = reader.readAll().get(columnNumber);
        return rowVal[columnNumber];
    }


}
