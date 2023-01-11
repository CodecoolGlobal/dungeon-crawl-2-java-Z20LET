package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.HashSet;
import java.util.Set;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        setHealth(20);
        setArmor(0);
        setDamage(1);
    }

    public Set<Item> inventory = new HashSet<>();

    @Override
    public String getTileName() {
        switch item
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getNeighbor(dx, dy).getType().equals(CellType.FLOOR) && cell.getNeighbor(dx, dy).getActor() == null)
        {
            Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public void fight(int dx, int dy) {
        Actor enemy = cell.getNeighbor(dx, dy).getActor();
        if (enemy != null) {
            enemy.setHealth(enemy.getHealth() - this.getDamage());
            if (enemy.getHealth() <= 0) kill(dx, dy);
        }
    }

    private void kill(int dx, int dy) {
        cell.getNeighbor(dx, dy).setActor(null);
    }
}
