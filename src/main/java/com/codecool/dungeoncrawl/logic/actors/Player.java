package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

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
            enemy.setHealth(enemy.getHealth() - 1);
            System.out.println(enemy.getHealth());
        }
    }
}
