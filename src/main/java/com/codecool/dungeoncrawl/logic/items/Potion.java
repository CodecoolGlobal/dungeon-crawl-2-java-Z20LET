package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Potion extends Item {

    public Potion(Cell cell) { super (cell); }


    @Override
    public String getTileName() {
        return "potion";
    }
    public void Heal() {
    }
}
