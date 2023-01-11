package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Armor extends Actor {

    public Armor(Cell cell) { super (cell); }

    @Override
    public String getTileName() {
        return "armor";
    }
}
