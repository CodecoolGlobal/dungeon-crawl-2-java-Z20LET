package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
public class Armor extends Item {

    public Armor(Cell cell) { super (cell); }

    @Override
    public String getTileName() {
        return "armor";
    }
}
