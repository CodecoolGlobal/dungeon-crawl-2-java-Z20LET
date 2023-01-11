package com.codecool.dungeoncrawl.logic.actors;

import java.util.Random;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0);

    private int dx;
    private int dy;
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static Direction randomDirection() {
        Random random = new Random();
        int randDir = random.nextInt(4);
        switch (randDir) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
                return LEFT;
            default:
                return null;
        }
    }
}
