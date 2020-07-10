package com.x.mowit;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.x.mowit.exception.DescriptionInvalidException;

public class MainTest {

    @Test
    void main_shouldThrowFileNotFoundException_withUnknownPathFileInArgs() {
        String[] args = new String[] {"unknownFile"};

        Assertions.assertThrows(
            FileNotFoundException.class,
            () -> Main.main(args)
        );
    }

    @Test
    void main_shouldBeOk_withKnownResourcePathFileInArgs()
        throws InterruptedException, IOException, DescriptionInvalidException {

        String[] args = new String[] {"src/main/resources/inputNoMoves.txt"};
        Main.main(args);
    }

    @Test
    void main_shouldBeOk_withDefaultBehavior_withoutArgs()
        throws InterruptedException, IOException, DescriptionInvalidException {

        String[] args = new String[] {};
        Main.main(args);
    }
}
