package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.HashSet;
import java.util.Set;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        setHealth(20);
        setArmor(0);
        setDamage(1);
    }

    static Set<Item> inventory = new HashSet<>();

    public static void setInventory(Item item) {
        Player.inventory.add(item);
    }

    @Override
    public String getTileName() {
        if (!inventory.stream().noneMatch(e -> e instanceof Armor) && inventory.stream().noneMatch(e -> e instanceof Sword)) {
            return "armored";
        } else if (inventory.stream().noneMatch(e -> e instanceof Armor) && !inventory.stream().noneMatch(e -> e instanceof Sword)) {
            return "sworded";
        } else if (!inventory.stream().noneMatch(e -> e instanceof Armor) && !inventory.stream().noneMatch(e -> e instanceof Sword)) {
            return "full";
        } else {
            return "default";
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getNeighbor(dx, dy).getType().equals(CellType.FLOOR) && cell.getNeighbor(dx, dy).getActor() == null) {
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
