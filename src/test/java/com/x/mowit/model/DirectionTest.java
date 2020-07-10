package com.x.mowit.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.x.mowit.exception.UnknownDirectionException;

public class DirectionTest {


    @Test
    void findByLabel_shouldThrowException_withUnknownDirectionIndication() {

        // GIVEN
        Character unknowDirection = 'Z';

        // WHEN / THEN
        Assertions.assertThrows(
            UnknownDirectionException.class,
            () -> Direction.findByLabel(unknowDirection)
        );
    }


    @Test
    void findByLabel_shouldReturnNorth_withNorthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'N';

        // WHEN
        Direction direction = Direction.findByLabel(directionIndication);

        // THEN
        assertThat(direction).isEqualTo(Direction.NORTH);
    }

    @Test
    void findByLabel_shouldReturnEast_withEastDirectionIndication() {

        // GIVEN
        Character directionIndication = 'E';

        // WHEN
        Direction direction = Direction.findByLabel(directionIndication);

        // THEN
        assertThat(direction).isEqualTo(Direction.EAST);
    }

    @Test
    void findByLabel_shouldReturnSouth_withSouthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'S';

        // WHEN
        Direction direction = Direction.findByLabel(directionIndication);

        // THEN
        assertThat(direction).isEqualTo(Direction.SOUTH);
    }

    @Test
    void findByLabel_shouldReturnWest_withWestDirectionIndication() {

        // GIVEN
        Character directionIndication = 'W';

        // WHEN
        Direction direction = Direction.findByLabel(directionIndication);

        // THEN
        assertThat(direction).isEqualTo(Direction.WEST);
    }


    @Test
    void findByOrdinal_shouldReturnWest_withWestDirectionIndication() {
        // GIVEN
        Character directionIndication = 'W';

        // WHEN
        Direction direction = Direction.findByLabel(directionIndication);

        // THEN
        assertThat(direction).isEqualTo(Direction.WEST);
    }

    @Test
    void shiftRight_shouldReturnEast_withNorthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'N';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftRightDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.EAST);
    }


    @Test
    void shiftRight_shouldReturnSouth_withEastDirectionIndication() {

        // GIVEN
        Character directionIndication = 'E';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftRightDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.SOUTH);
    }


    @Test
    void shiftRight_shouldReturnWest_withSouthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'S';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftRightDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.WEST);
    }


    @Test
    void shiftRight_shouldReturnNorth_withWestDirectionIndication() {

        // GIVEN
        Character directionIndication = 'W';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftRightDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.NORTH);
    }



    @Test
    void shiftLeft_shouldReturnWest_withNorthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'N';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftLeftDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.WEST);
    }


    @Test
    void shiftLeft_shouldReturnSouth_withWestDirectionIndication() {

        // GIVEN
        Character directionIndication = 'W';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftLeftDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.SOUTH);
    }


    @Test
    void shiftLeft_shouldReturnEast_withSouthDirectionIndication() {

        // GIVEN
        Character directionIndication = 'S';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftLeftDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.EAST);
    }


    @Test
    void shiftLeft_shouldReturnNorth_withEastDirectionIndication() {

        // GIVEN
        Character directionIndication = 'E';
        Direction direction = Direction.findByLabel(directionIndication);

        // WHEN
        Direction newDirection = direction.getShiftLeftDirection();

        // THEN
        assertThat(newDirection).isEqualTo(Direction.NORTH);
    }

    @Test
    void toString_shouldReturnLabels() {

        // GIVEN
        Direction north = Direction.NORTH;
        Direction east = Direction.EAST;
        Direction south = Direction.SOUTH;
        Direction west = Direction.WEST;

        // WHEN / THEN
        assertThat(north.toString()).isEqualTo("N");
        assertThat(east.toString()).isEqualTo("E");
        assertThat(south.toString()).isEqualTo("S");
        assertThat(west.toString()).isEqualTo("W");
    }
}
