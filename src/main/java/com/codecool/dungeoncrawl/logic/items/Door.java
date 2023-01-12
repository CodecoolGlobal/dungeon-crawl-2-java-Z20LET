package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Door extends Item {

    public Door(Cell cell) { super (cell); }

    @Override
    public void interact(Player player) {
    }

    @Override
    public String getTileName() {
        if(Player.inventory.stream().noneMatch(e -> e instanceof BlueKey)){
            return "door-close";
        } else{
            return "door-open";
        }
    }
}
