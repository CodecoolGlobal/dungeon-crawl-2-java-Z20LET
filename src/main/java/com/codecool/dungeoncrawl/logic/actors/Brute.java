package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Brute extends Actor {
    public Brute(Cell cell) {
        super(cell);
        setHealth(26);
        setArmor(2);
        setDamage(7);
    }

    @Override
    public String getTileName() {
        return "brute";
    }

    @Override
    public void move(int dx, int dy) {
        Direction dir = Direction.randomDirection();
        if (cell.getNeighbor(dir.getDx(), dir.getDy()).getType().equals(CellType.FLOOR) && cell.getNeighbor(dir.getDx(), dir.getDy()).getActor() == null)
        {
            Cell nextCell = cell.getNeighbor(dir.getDx(), dir.getDy());
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public void fight(int dx, int dy) {
        Actor enemy = cell.getNeighbor(dx, dy).getActor();
        if (enemy instanceof Player) {
            int armor = enemy.getArmor();
            if (armor < getDamage()) enemy.setHealth(enemy.getHealth() - (this.getDamage() - armor));
        }
    }
}
