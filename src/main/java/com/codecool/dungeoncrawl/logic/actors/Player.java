package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private int health = getHealth();
    private int armor = getArmor();
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    @Override
    public void fight(int dx, int dy) {
        Actor enemy = cell.getNeighbor(dx, dy).getActor();
        if (enemy != null) {
            enemy.setHealth(enemy.getHealth() - 1);
        }
        // System.out.println(enemy.getHealth());
    }
}
