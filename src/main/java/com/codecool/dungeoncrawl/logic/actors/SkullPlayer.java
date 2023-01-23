package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class SkullPlayer extends Actor {
    public SkullPlayer(Cell cell) {
        super(cell);
        setHealth(Integer.MAX_VALUE);
        setArmor(Integer.MAX_VALUE);
        setDamage(0);
    }

    @Override
    public String getTileName() {
        return "dead";
    }

    @Override
    public void move(int dx, int dy) {}

    @Override
    public void fight(int dx, int dy) {}
}
