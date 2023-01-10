package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health = 10;

    private int armor = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void act(int dx, int dy) {
        move(dx, dy);
        checkFight(dx, dy);
    }

    public abstract void move(int dx, int dy);

    public abstract void fight(int dx, int dy);

    public void checkFight(int dx, int dy) {
        fight(dx, dy);
        Actor enemy = cell.getNeighbor(dx, dy).getActor();
        if (enemy != null) enemy.fight(-dx, -dy);
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
