package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

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

}
