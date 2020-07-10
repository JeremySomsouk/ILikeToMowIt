package com.x.mowit.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.x.mowit.exception.DescriptionInvalidException;

public class FileReaderUtilTest {


    @Test
    void getLinesFromResourceInputFile_shouldReadResourceInputFile() throws IOException, DescriptionInvalidException {

        // GIVEN
        // WHEN
        List<String> linesFromResourceInputFile = FileReaderUtil.getLinesFromResourceInputFile();

        // THEN
        assertThat(linesFromResourceInputFile).hasSize(5);
    }


    @Test
    void parseInputFile_shouldThrowFileNotFoundException_withUnknownFilePath() {

        // GIVEN
        String unknownFilePath = "unknownFilePath";

        // WHEN / THEN
        Assertions.assertThrows(FileNotFoundException.class, () -> FileReaderUtil.parseInputFile(unknownFilePath));
    }


    @Test
    void parseInputFile_shouldReadFile_withGivenFilePath() throws IOException, DescriptionInvalidException {

        // GIVEN
        File file = new File(getClass().getClassLoader().getResource("inputNoMoves.txt").getFile());
        String filePath = file.getPath();

        // WHEN
        List<String> linesFromResourceInputFile = FileReaderUtil.parseInputFile(filePath);

        // THEN
        assertThat(linesFromResourceInputFile).hasSize(9);
    }


    @Test
    void parseInputFile_shouldThrowDescriptionInvalidException_withEmptyFile() {

        // GIVEN
        File file = new File(getClass().getClassLoader().getResource("emptyInput.txt").getFile());
        String filePath = file.getPath();

        // WHEN / THEN
        Assertions.assertThrows(DescriptionInvalidException.class, () -> FileReaderUtil.parseInputFile(filePath));
    }

}
