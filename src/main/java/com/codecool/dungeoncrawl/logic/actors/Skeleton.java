package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        setHealth(8);
        setArmor(0);
        setDamage(1);
    }

    @Override
    public String getTileName() {
        return "skeleton";
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
