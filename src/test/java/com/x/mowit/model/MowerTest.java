package com.x.mowit.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MowerTest {

    @Test
    void launchMower_shouldNotMoveMowers_withFacingWalls() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 5, 5, 'N', Instruction.toInstructionQueue("F")),
            new Mower(2, 5, 0, 'E', Instruction.toInstructionQueue("F")),
            new Mower(3, 0, 0, 'S', Instruction.toInstructionQueue("F")),
            new Mower(4, 0, 5, 'W', Instruction.toInstructionQueue("F"))
        );
        Grid grid = new Grid(5, 5);
        grid.initGridOccupation(mowers);

        // WHEN
        for (Mower mower : mowers) {
            mower.launchMower(grid);
        }

        // THEN
        assertThat(mowers.get(0).getXPosition()).isEqualTo(5);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(5);

        assertThat(mowers.get(1).getXPosition()).isEqualTo(5);
        assertThat(mowers.get(1).getYPosition()).isEqualTo(0);

        assertThat(mowers.get(2).getXPosition()).isEqualTo(0);
        assertThat(mowers.get(2).getYPosition()).isEqualTo(0);

        assertThat(mowers.get(3).getXPosition()).isEqualTo(0);
        assertThat(mowers.get(3).getYPosition()).isEqualTo(5);
    }

    @Test
    void launchMower_shouldNotMoveMowers_withFacingMowers() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 2, 3, 'E', Instruction.toInstructionQueue("F")),
            new Mower(2, 3, 3, 'W', Instruction.toInstructionQueue("F"))
        );
        Grid grid = new Grid(5, 5);
        grid.initGridOccupation(mowers);

        // WHEN
        for (Mower mower : mowers) {
            mower.launchMower(grid);
        }

        // THEN
        assertThat(mowers.get(0).getXPosition()).isEqualTo(2);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(3);

        assertThat(mowers.get(1).getXPosition()).isEqualTo(3);
        assertThat(mowers.get(1).getYPosition()).isEqualTo(3);
    }

    @Test
    void launchMower_shouldMoveMowersOrBlockWhenNeededSequentially_withMowersPositioned() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 2, 5, 'N', Instruction.toInstructionQueue("LF")),
            new Mower(2, 0, 3, 'W', Instruction.toInstructionQueue("F")),
            new Mower(3, 5, 4, 'E', Instruction.toInstructionQueue("F")),
            new Mower(4, 5, 1, 'S', Instruction.toInstructionQueue("FRFFFFF"))
        );
        Grid grid = new Grid(5, 5);
        grid.initGridOccupation(mowers);

        // WHEN
        for (Mower mower : mowers) {
            mower.launchMower(grid);
        }

        // THEN
        assertThat(mowers.get(0).getXPosition()).isEqualTo(1);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(5);

        assertThat(mowers.get(1).getXPosition()).isEqualTo(0);
        assertThat(mowers.get(1).getYPosition()).isEqualTo(3);

        assertThat(mowers.get(2).getXPosition()).isEqualTo(5);
        assertThat(mowers.get(2).getYPosition()).isEqualTo(4);

        assertThat(mowers.get(3).getXPosition()).isEqualTo(0);
        assertThat(mowers.get(3).getYPosition()).isEqualTo(0);
    }


    @Test
    void toString_shouldDisplayMowerPosition() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 2, 5, 'N', Instruction.toInstructionQueue("LF"))
        );
        Grid grid = new Grid(5, 5);
        grid.initGridOccupation(mowers);

        // WHEN
        for (Mower mower : mowers) {
            mower.launchMower(grid);
        }

        // THEN
        String expectedOutput = "1 5 W";
        assertThat(mowers.get(0).getXPosition()).isEqualTo(1);
        assertThat(mowers.get(0).getYPosition()).isEqualTo(5);
        assertThat(mowers.get(0).getMowerNumber()).isEqualTo(1);
        assertThat(mowers.get(0).toString()).isEqualTo(expectedOutput);
    }
}
