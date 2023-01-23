package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Armor extends Item {

    public Armor(Cell cell) { super (cell); }


    @Override
    public String getTileName() {
        return "armor";
    }

    @Override
    public void interact(Player player){
        player.setArmor(player.getArmor() + 1);
    }
}
