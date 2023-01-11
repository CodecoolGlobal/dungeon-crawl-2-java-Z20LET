package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class BlueKey extends Item {

    public BlueKey(Cell cell) { super (cell); }

    @Override
    public String getTileName() {
        return "key";
    }
}
