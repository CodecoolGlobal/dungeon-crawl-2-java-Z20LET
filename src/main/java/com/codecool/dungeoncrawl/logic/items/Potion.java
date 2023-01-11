package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Potion extends Actor {

    public Potion(Cell cell) { super (cell); }

    @Override
    public String getTileName() {
        return "potion";
    }
}
