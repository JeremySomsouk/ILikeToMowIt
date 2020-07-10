package com.x.mowit.model;

import java.util.Queue;

public class Mower {

    private final Integer mowerNumber;
    private Integer xPosition;
    private Integer yPosition;
    private Direction direction;
    private final Queue<Instruction> instructions;

    public Mower(Integer mowerNumber, Integer xPosition, Integer yPosition, Character directionIndication,
                 Queue<Instruction> instructions) {
        this.mowerNumber = mowerNumber;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = Direction.findByLabel(directionIndication);
        this.instructions = instructions;
    }

    public void launchMower(Grid grid) {

        while (instructions.peek() != null) {
            Instruction currentInstruction = instructions.poll();
            currentInstruction.handle(this, grid);
        }
    }

    public Integer getMowerNumber() {
        return mowerNumber;
    }

    public Integer getXPosition() {
        return xPosition;
    }

    public void setXPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public void setYPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Queue<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return this.xPosition + " " + this.yPosition + " " + this.direction;
    }
}