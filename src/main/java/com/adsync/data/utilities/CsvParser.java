package com.adsync.data.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static List<String[]> csvReader(String csvFile) throws IOException {
        String line = "";
        int lineNumber = 0;
        String cvsSplitBy = ",";
        List<String[]> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                arr.add(line.split(cvsSplitBy));

            }
        }return arr;
    }
}
