package com.x.mowit.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.x.mowit.exception.UnknownInstructionException;

public class InstructionTest {

    @Test
    void findByLabel_shouldThrowUnknownInstructionException_withUnknownInstruction() {

        // GIVEN
        char unknownInstruction = 'Z';

        // WHEN / THEN
        Assertions.assertThrows(
            UnknownInstructionException.class,
            () -> Instruction.findByLabel(unknownInstruction)
        );
    }


    @Test
    void findByLabel_shouldReturn_withUnknownInstruction() {

        // GIVEN
        char rotateRightInstruction = 'R';
        char rotateLeftInstruction = 'L';
        char moveForwardInstruction = 'F';

        // WHEN / THEN
        assertThat(Instruction.findByLabel(rotateRightInstruction)).isEqualTo(Instruction.ROTATE_RIGHT);
        assertThat(Instruction.findByLabel(rotateLeftInstruction)).isEqualTo(Instruction.ROTATE_LEFT);
        assertThat(Instruction.findByLabel(moveForwardInstruction)).isEqualTo(Instruction.MOVE_FORWARD);
    }
}
