package com.codecool.dungeoncrawl.logic.items;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

abstract class Item implements Drawable {

    public Item(Cell cell) { this.cell = cell; }

    public Cell cell;

    @Override
    public String getTileName() {
        return " ";
    }

}
