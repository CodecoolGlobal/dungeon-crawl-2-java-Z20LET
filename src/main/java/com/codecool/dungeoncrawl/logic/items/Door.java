package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Door extends Item {

    private Player player;
    public Door(Cell cell, Player player) {
        super (cell);
        this.player = player;
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public String getTileName() {
        if(player == null || !player.hasKey()){
            return "door-close";
        } else{
            return "door-open";
        }
    }
}
