package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Orc extends Actor {
    Direction currentDir = Direction.UP;

    public Orc(Cell cell) {
        super(cell);
        setHealth(7);
        setArmor(2);
        setDamage(3);
    }

    @Override
    public String getTileName() {
        return "orc";
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getNeighbor(currentDir.getDx(), currentDir.getDy()).getActor() == null)
        {
            switch (currentDir) {
                case UP:
                    currentDir = Direction.DOWN;
                    break;
                default:
                    currentDir = Direction.UP;
            }
            Cell nextCell = cell.getNeighbor(currentDir.getDx(), currentDir.getDy());
            if (nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
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
