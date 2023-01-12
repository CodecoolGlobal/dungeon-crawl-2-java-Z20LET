package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Guard extends Actor {
    Direction currentDir = Direction.LEFT;

    public Guard(Cell cell) {
        super(cell);
        setHealth(9);
        setArmor(1);
        setDamage(2);
    }

    @Override
    public String getTileName() {
        return "guard";
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getNeighbor(currentDir.getDx(), currentDir.getDy()).getType().equals(CellType.FLOOR) && cell.getNeighbor(currentDir.getDx(), currentDir.getDy()).getActor() == null)
        {
            switch (currentDir) {
                case LEFT:
                    currentDir = Direction.RIGHT;
                    break;
                default:
                    currentDir = Direction.LEFT;
            }
            Cell nextCell = cell.getNeighbor(currentDir.getDx(), currentDir.getDy());
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if (nextCell.getActor() == null) {

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
