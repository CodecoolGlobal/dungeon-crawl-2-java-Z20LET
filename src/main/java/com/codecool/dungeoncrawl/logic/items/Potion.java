package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Potion extends Actor {

    public Potion(Cell cell) { super (cell); }

    @Override
    public void move(int dx, int dy) {

    }

    @Override
    public void fight(int dx, int dy) {

    }

    @Override
    public String getTileName() {
        return "potion";
    }
    public void Heal() {
    }
}
