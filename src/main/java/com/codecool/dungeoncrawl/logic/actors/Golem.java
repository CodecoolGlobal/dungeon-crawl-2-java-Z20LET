package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Golem extends Actor {
    public Golem(Cell cell) {
        super(cell);
        setHealth(14);
        setArmor(3);
        setDamage(3);
    }

    @Override
    public String getTileName() {
        return "golem";
    }

    @Override
    public void move(int dx, int dy) {}

    @Override
    public void fight(int dx, int dy) {
        Actor enemy = cell.getNeighbor(dx, dy).getActor();
        if (enemy instanceof Player) {
            int armor = enemy.getArmor();
            if (armor < getDamage()) enemy.setHealth(enemy.getHealth() - (this.getDamage() - armor));
        }
    }
}
