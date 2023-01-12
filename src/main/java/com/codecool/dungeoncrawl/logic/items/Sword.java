package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Sword extends Item {

    public Sword(Cell cell) { super (cell); }

    @Override
    public void interact(Player player) {
        player.setDamage(player.getDamage() + 1);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
