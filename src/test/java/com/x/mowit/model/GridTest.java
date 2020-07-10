package com.x.mowit.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GridTest {

    @Test
    void initGridOccupation_shouldBlockGridCasesWithMowers_withMowersPositionned() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 5, 5, 'N', Instruction.toInstructionQueue("FFFF")),
            new Mower(2, 1, 3, 'W', Instruction.toInstructionQueue("FFFF")),
            new Mower(3, 5, 4, 'E', Instruction.toInstructionQueue("FFFF")),
            new Mower(4, 2, 5, 'S', Instruction.toInstructionQueue("FFFF"))
        );
        Grid grid = new Grid(5, 5);

        // WHEN
        grid.initGridOccupation(mowers);

        // THEN
        assertThat(grid.isNextPositionOpen(5, 5)).isFalse();
        assertThat(grid.isNextPositionOpen(1, 3)).isFalse();
        assertThat(grid.isNextPositionOpen(5, 4)).isFalse();
        assertThat(grid.isNextPositionOpen(2, 5)).isFalse();
    }

    @Test
    void setNextPositionAndReleaseOld_shouldReleasePreviousPosition() {

        // GIVEN
        List<Mower> mowers = List.of(
            new Mower(1, 5, 5, 'S', Instruction.toInstructionQueue("FFFF"))
        );
        Grid grid = new Grid(5, 5);

        // WHEN / THEN
        grid.initGridOccupation(mowers);
        assertThat(grid.isNextPositionOpen(5, 5)).isFalse();
        assertThat(grid.isNextPositionOpen(5, 4)).isTrue();

        grid.setNextPositionAndReleaseOld(5, 4, 5, 5);
        assertThat(grid.isNextPositionOpen(5, 5)).isTrue();
        assertThat(grid.isNextPositionOpen(5, 4)).isFalse();
    }
}
