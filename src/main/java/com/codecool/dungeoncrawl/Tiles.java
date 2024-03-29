package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;


public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("water", new Tile(8,5));
        tileMap.put("door-open", new Tile(2,9));
        tileMap.put("door-close", new Tile(1,9));
        tileMap.put("default", new Tile(25, 0));
        tileMap.put("armored", new Tile(26, 0));
        tileMap.put("sworded", new Tile(27, 0));
        tileMap.put("full", new Tile(28, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("guard", new Tile(28, 6));
        tileMap.put("orc", new Tile(25, 9));
        tileMap.put("golem", new Tile(30, 6));
        tileMap.put("axolotl", new Tile(18, 8));
        tileMap.put("brute", new Tile(27, 2));
        //decor
        tileMap.put("skull", new Tile(22, 23));
        tileMap.put("dead", new Tile(18, 24));
        //items
        tileMap.put("key", new Tile(17, 23));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("armor", new Tile(6, 24));
        tileMap.put("potion", new Tile(16, 25));
        //temp demo letters
        tileMap.put("demod", new Tile(22, 30));
        tileMap.put("demoe", new Tile(23, 30));
        tileMap.put("demom", new Tile(31, 30));
        tileMap.put("demoo", new Tile(20, 31));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
