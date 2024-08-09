package au.com.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVFile {
    public static String[] cSVDataRead(String path) {
        String[] data = new String[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true;
            List<String> headers = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                data = line.split(",");

                if (isFirstLine) {
                    // Assuming the first line is the header
                    for (String header : data) {
                        headers.add(header);
                    }
                    isFirstLine = false;
                } else {
                    // Process data rows
                    for (int i = 0; i < data.length; i++) {
                        String header = headers.get(i);
                        String value = data[i];
                        System.out.println(header + ": " + value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}