package com.x.mowit.model;

import java.util.List;

public class Grid {

    private final int maxWidth;
    private final int maxHeight;
    private boolean[][] gridOccupation;

    public Grid(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void initGridOccupation(List<Mower> mowers) {

        boolean[][] gridOccupation = new boolean[this.maxWidth + 1][this.maxHeight + 1];

        for (Mower mower : mowers) {
            Integer xIndex = mower.getXPosition();
            Integer yIndex = mower.getYPosition();
            gridOccupation[xIndex][yIndex] = true;
        }

        this.gridOccupation = gridOccupation;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public synchronized boolean checkAndSetNextPosition(int nextX, int nextY, int oldX, int oldY) {

        boolean isNextPositionOpen = isNextPositionOpen(nextX, nextY);

        if (isNextPositionOpen) {
            setNextPositionAndReleaseOld(nextX, nextY, oldX, oldY);
        }

        return isNextPositionOpen;
    }

    boolean isNextPositionOpen(int nextX, int nextY) {
        return isNextPositionInsideBorders(nextX, nextY) && isNextPositionFree(nextX, nextY);
    }

    private boolean isNextPositionInsideBorders(int x, int y) {
        return x >= 0 && y >= 0 && x <= maxWidth && y <= maxHeight;
    }

    private boolean isNextPositionFree(int x, int y) {
        return !this.gridOccupation[x][y];
    }

    private void setNextPositionAndReleaseOld(int nextX, int nextY, int oldX, int oldY) {
        this.gridOccupation[nextX][nextY] = true;
        this.gridOccupation[oldX][oldY] = false;
    }
}
