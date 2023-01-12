package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WATER("water"),
    WALL("wall"),
    SKULL("skull"),
    //temp demo letter types
    DEMOD("demod"),
    DEMOE("demoe"),
    DEMOM("demom"),
    DEMOO("demoo");
    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
