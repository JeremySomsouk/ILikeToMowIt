package com.x.mowit.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.x.mowit.exception.DescriptionInvalidException;
import com.x.mowit.model.Direction;
import com.x.mowit.model.Grid;
import com.x.mowit.model.Mower;

public class DescriptionsParserUtilTest {

    @Test
    void parseGridDescription_shouldThrowDescriptionInvalidException_withExtraBeginningSpace() {

        // GIVEN
        List<String> descriptions = List.of(" 5 5");

        // WHEN / THEN
        Assertions.assertThrows(
            DescriptionInvalidException.class,
            () -> DescriptionsParserUtil.parseGridDescription(descriptions)
        );
    }


    @Test
    void parseGridDescription_shouldReturnValidGrid_withValidInput() throws DescriptionInvalidException {

        // GIVEN
        List<String> descriptions = List.of("5 4");

        // WHEN
        Grid grid = DescriptionsParserUtil.parseGridDescription(descriptions);

        // THEN
        assertThat(grid.getMaxWidth()).isEqualTo(5);
        assertThat(grid.getMaxHeight()).isEqualTo(4);
    }

    @Test
    void parseMowerDescriptions_shouldThrowDescriptionInvalidException_withExtraBeginningSpace() {

        // GIVEN
        List<String> descriptions = List.of("5 5", " 1 2 N", "LFLFLFLF");

        // WHEN / THEN
        Assertions.assertThrows(
            DescriptionInvalidException.class,
            () -> DescriptionsParserUtil.parseMowerDescriptions(descriptions)
        );
    }


    @Test
    void parseMowerDescriptions_shouldReturnValidMower_withValidInput() throws DescriptionInvalidException {

        // GIVEN
        List<String> descriptions = List.of("5 5", "1 2 N", "LFLFLFLF");

        // WHEN
        List<Mower> mowers = DescriptionsParserUtil.parseMowerDescriptions(descriptions);

        // THEN
        assertThat(mowers).hasSize(1);
        assertThat(mowers.get(0).getXPosition()).isEqualTo(1);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(2);
        assertThat(mowers.get(0).getDirection()).isEqualTo(Direction.NORTH);
        assertThat(mowers.get(0).getInstructions()).hasSize(8);
    }

    @Test
    void parseMowerDescriptions_shouldReturnValidMowers_withTwoValidInputMowers() throws DescriptionInvalidException {

        // GIVEN
        List<String> descriptions = List.of("5 5", "1 2 N", "LFLFLFLF", "3 3 E", "FFRFFRFRRF");

        // WHEN
        List<Mower> mowers = DescriptionsParserUtil.parseMowerDescriptions(descriptions);

        // THEN
        assertThat(mowers).hasSize(2);
        assertThat(mowers.get(0).getXPosition()).isEqualTo(1);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(2);
        assertThat(mowers.get(0).getDirection()).isEqualTo(Direction.NORTH);
        assertThat(mowers.get(0).getInstructions()).hasSize(8);

        assertThat(mowers.get(1).getXPosition()).isEqualTo(3);
        assertThat(mowers.get(1).getYPosition()).isEqualTo(3);
        assertThat(mowers.get(1).getDirection()).isEqualTo(Direction.EAST);
        assertThat(mowers.get(1).getInstructions()).hasSize(10);
    }
}
