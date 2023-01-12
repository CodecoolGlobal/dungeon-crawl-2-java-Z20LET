package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Potion extends Item {

    public Potion(Cell cell) { super (cell); }

    @Override
    public void interact(Player player) {
        player.setHealth(player.getHealth() + 10);
    }


    @Override
    public String getTileName() {
        return "potion";
    }

}
