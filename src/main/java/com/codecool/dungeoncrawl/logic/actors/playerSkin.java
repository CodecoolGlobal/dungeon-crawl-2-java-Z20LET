package com.codecool.dungeoncrawl.logic.actors;

public enum playerSkin {
    DEFAULT("default"),
    ARMORED("armored"),
    SWORDED("sworded"),
    FULL("full");

    public String skinName;

    playerSkin(String skinName) {
        this.skinName = skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinName() {
        return skinName;
    }
}
