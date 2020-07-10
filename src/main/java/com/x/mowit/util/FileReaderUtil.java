package com.x.mowit.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.x.mowit.exception.DescriptionInvalidException;

public class FileReaderUtil {

    public static List<String> getLinesFromResourceInputFile() throws IOException, DescriptionInvalidException {

        InputStreamReader input = new InputStreamReader(FileReaderUtil.class.getResourceAsStream("/input.txt"));

        return extractLinesFromInput(input);
    }

    public static List<String> parseInputFile(String filePath) throws IOException, DescriptionInvalidException {

        FileReader input = new FileReader(filePath);

        return extractLinesFromInput(input);
    }

    private static List<String> extractLinesFromInput(Reader input) throws IOException, DescriptionInvalidException {

        List<String> fileLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(input)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
        }

        if (fileLines.isEmpty()) {
            throw new DescriptionInvalidException("The input file is invalid, no instruction given");
        }

        return fileLines;
    }
}
