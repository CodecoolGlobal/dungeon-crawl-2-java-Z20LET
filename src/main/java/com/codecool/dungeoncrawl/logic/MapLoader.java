package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapLoader {
    static Map<CellType, Character> cells = new HashMap<>();
    static Map<Class, Character> items = new HashMap<>();
    static Map<Class, Character> actors = new HashMap<>();

    private static void FillTypes() {
        cells.put(CellType.EMPTY, ' ');
        cells.put(CellType.FLOOR, '.');
        cells.put(CellType.WATER, 'w');
        cells.put(CellType.WALL, '#');
        cells.put(CellType.SKULL, 'o');
        cells.put(CellType.DOOR, 'C');
        cells.put(CellType.DEMOD, 'D');
        cells.put(CellType.DEMOE, 'E');
        cells.put(CellType.DEMOM, 'M');
        cells.put(CellType.DEMOO, 'O');
        items.put(Sword.class, 'S');
        items.put(Armor.class, 'a');
        items.put(Potion.class, 'p');
        items.put(BlueKey.class, 'k');
        items.put(Door.class, 'C');
        actors.put(Player.class, '@');
        actors.put(Skeleton.class, 's');
        actors.put(Guardian.class, 'g');
        actors.put(Orc.class, 'r');
        actors.put(Golem.class, 'l');
        actors.put(Axolotl.class, 'x');
        actors.put(Brute.class, 'b');
    }

    public static GameMap loadMap(InputStream is) {
        int width;
        int height;
        // InputStream is = new ByteArrayInputStream(mapStr.getBytes());
        if (is == null) is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);

        width = scanner.nextInt();
        height = scanner.nextInt();
        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case 'C':
                            cell.setType(CellType.FLOOR);
                            new Door(cell, map.getPlayer());
                            break;
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'D':
                            cell.setType(CellType.DEMOD);
                            break;
                        case 'E':
                            cell.setType(CellType.DEMOE);
                            break;
                        case 'M':
                            cell.setType(CellType.DEMOM);
                            break;
                        case 'O':
                            cell.setType(CellType.DEMOO);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Skeleton(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Guardian(cell));
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Orc(cell));
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Golem(cell));
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Axolotl(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.addActor(new Brute(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'o':
                            cell.setType(CellType.SKULL);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new BlueKey(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static String mapToString(GameMap map) {
        FillTypes();
        String result = map.getWidth() + " " + map.getHeight() + "\n";
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.getCell(x, y).getItem() == null) {
                    if (map.getCell(x, y).getActor() == null) {
                        result += cells.get(map.getCell(x, y).getType());
                    }
                    else result += actors.get(map.getCell(x, y).getActor().getClass());
                }
                else result += items.get(map.getCell(x, y).getItem().getClass());
            }
            if (y != map.getHeight()) result += '\n';
        }
        return result;
    }
}
