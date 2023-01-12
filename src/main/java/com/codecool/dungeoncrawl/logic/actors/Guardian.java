package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Guardian extends Actor {
    Direction currentDir = Direction.LEFT;

    public Guardian(Cell cell) {
        super(cell);
        setHealth(9);
        setArmor(2);
        setDamage(2);
    }

    @Override
    public String getTileName() {
        return "guard";
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getNeighbor(currentDir.getDx(), currentDir.getDy()).getActor() == null)
        {
            switch (currentDir) {
                case LEFT:
                    currentDir = Direction.RIGHT;
                    break;
                default:
                    currentDir = Direction.LEFT;
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
