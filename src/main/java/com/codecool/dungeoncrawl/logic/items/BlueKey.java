package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class BlueKey extends Actor {

    public BlueKey(Cell cell) { super (cell); }

    @Override
    public String getTileName() {
        return "key";
    }
}
