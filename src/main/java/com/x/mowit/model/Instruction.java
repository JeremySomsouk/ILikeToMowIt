package com.x.mowit.model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.BiConsumer;

import com.x.mowit.exception.UnknownInstructionException;

public enum Instruction {

    ROTATE_RIGHT('R', Instruction::handleRotateRight),
    ROTATE_LEFT('L', Instruction::handleRotateLeft),
    MOVE_FORWARD('F', Instruction::handleMoveForward);

    private final Character label;
    private final BiConsumer<Mower, Grid> updateHandler;

    Instruction(Character label, BiConsumer<Mower, Grid> updateHandler) {
        this.label = label;
        this.updateHandler = updateHandler;
    }

    public void handle(Mower mower, Grid grid) {
        this.updateHandler.accept(mower, grid);
    }

    public static Instruction findByLabel(Character instructionIndication) {

        for (Instruction instruction : values()) {
            if (instruction.label.equals(instructionIndication)) {
                return instruction;
            }
        }

        throw new UnknownInstructionException("Unknown instruction to find by label.");
    }

    public static Queue<Instruction> toInstructionQueue(String description) {

        Queue<Instruction> instructions = new ArrayDeque<>();

        for (Character c : description.toCharArray()) {
            instructions.add(findByLabel(c));
        }

        return instructions;
    }

    private static void handleRotateRight(Mower mower, Grid grid) {
        mower.setDirection(mower.getDirection().getShiftRightDirection());
    }

    private static void handleRotateLeft(Mower mower, Grid grid) {
        mower.setDirection(mower.getDirection().getShiftLeftDirection());
    }

    private static void handleMoveForward(Mower mower, Grid grid) {

        Direction direction = mower.getDirection();

        int nextX = mower.getXPosition() + direction.getXIncrement();
        int nextY = mower.getYPosition() + direction.getYIncrement();

        if (grid.checkAndSetNextPosition(nextX, nextY, mower.getXPosition(), mower.getYPosition())) {
            mower.setXPosition(nextX);
            mower.setYPosition(nextY);
        }
    }
}
