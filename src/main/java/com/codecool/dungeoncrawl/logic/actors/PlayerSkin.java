package com.codecool.dungeoncrawl.logic.actors;

public enum PlayerSkin {
    DEFAULT("default"),
    ARMORED("armored"),
    SWORDED("sworded"),
    FULL("full");

    public String skinName;

    PlayerSkin(String skinName) {
        this.skinName = skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinName() {
        return skinName;
    }
}
