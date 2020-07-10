package com.x.mowit.model;

import com.x.mowit.exception.UnknownDirectionException;

public enum Direction {

    NORTH('N', 0, 0, 1),
    EAST('E', 1, 1, 0),
    SOUTH('S', 2, 0, -1),
    WEST('W', 3, -1, 0);

    private final Character label;
    private final Integer ordinal;
    private final Integer xIncrement;
    private final Integer yIncrement;

    Direction(Character label, Integer ordinal, Integer xIncrement, Integer yIncrement) {
        this.label = label;
        this.ordinal = ordinal;
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;
    }

    public static Direction findByLabel(Character directionIndication) {

        for (Direction direction : values()) {
            if (direction.label.equals(directionIndication)) {
                return direction;
            }
        }

        throw new UnknownDirectionException("Unknown direction to find by label.");
    }

    public Direction getShiftRightDirection() {
        return findByOrdinal((ordinal + 1) % 4);
    }

    public Direction getShiftLeftDirection() {
        return findByOrdinal((ordinal + 3) % 4);
    }

    public Integer getXIncrement() {
        return xIncrement;
    }

    public Integer getYIncrement() {
        return yIncrement;
    }

    @Override
    public String toString() {
        return String.valueOf(this.label);
    }

    private Direction findByOrdinal(Integer ordinal) {

        for (Direction direction : values()) {
            if (direction.ordinal.equals(ordinal)) {
                return direction;
            }
        }

        throw new UnknownDirectionException("Unknown direction to find by ordinal.");
    }
}