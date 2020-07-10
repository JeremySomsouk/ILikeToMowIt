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

    public synchronized void setNextPositionAndReleaseOld(int nextX, int nextY, int oldX, int oldY) {
        this.gridOccupation[nextX][nextY] = true;
        this.gridOccupation[oldX][oldY] = false;
    }

    public boolean isNextPositionOpen(int x, int y) {
        return isNextPositionInsideBorders(x, y) && isNextPositionFree(x, y);
    }

    private boolean isNextPositionInsideBorders(int x, int y) {
        return x >= 0 && y >= 0 && x <= maxWidth && y <= maxHeight;
    }

    private synchronized boolean isNextPositionFree(int x, int y) {
        return !this.gridOccupation[x][y];
    }
}
