package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Axolotl extends Actor {
    public Axolotl(Cell cell) {
        super(cell);
        setHealth(4);
        setArmor(0);
        setDamage(0);
    }

    @Override
    public String getTileName() {
        return "axolotl";
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
    public void fight(int dx, int dy) {}
}
