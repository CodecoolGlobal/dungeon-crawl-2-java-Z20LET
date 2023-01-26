package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.SkullPlayer;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.modal.Modal;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

public class Main extends Application {
    InputStream mapSource = MapLoader.class.getResourceAsStream("/map.txt");
    GameMap map = MapLoader.loadMap(mapSource);
    Player player;
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label armorLabel = new Label();
    Label damageLabel = new Label();
    Label inventoryLabel = new Label();

    Stage primaryStage;

    GameDatabaseManager dbManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDbManager();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Armor: "), 0, 5);
        ui.add(new Label("Damage: "), 0, 10);
        ui.add(new Label("Inventory: "), 0, 15);
        ui.add(new Label("←↑→↓ - Movement"), 0, 20);
        ui.add(new Label("space - Pickup"), 0, 25);
        ui.add(new Label("R - Respawn"), 0, 30);
        ui.add(new Label("S - save to SQL"), 0, 35);
        ui.add(new Label("E - export JSON"), 0, 40);
        ui.add(new Label("L - load"), 0, 45);
        ui.add(new Label("DEMO version"), 0, 50);

        ui.add(healthLabel, 1, 0);
        ui.add(armorLabel, 1, 5);
        ui.add(damageLabel, 1, 10);
        ui.add(inventoryLabel, 1, 15);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        player = map.getPlayer();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        //scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Blue Dungeon - Demo");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (checkPlayerDead()) {
            healthLabel.setText("KO!");
            int deathX = player.getCell().getX();
            int deathY = player.getCell().getY();
            if (keyEvent.getCode() == KeyCode.R) {
                map = MapLoader.loadMap(mapSource);
                player = map.getPlayer();
                map.getCell(deathX, deathY).setActor(new SkullPlayer(map.getCell(deathX, deathY)));
                player.emptyInventory();
                refresh();
            }
        } else {
            switch (keyEvent.getCode()) {
                case UP:
                    player.act(0, -1);
                    refreshActors();
                    refresh();
                    break;
                case DOWN:
                    player.act(0, 1);
                    refreshActors();
                    refresh();
                    break;
                case LEFT:
                    player.act(-1, 0);
                    refreshActors();
                    refresh();
                    break;
                case RIGHT:
                    player.act(1, 0);
                    refreshActors();
                    refresh();
                    break;
                case SPACE:
                    if (player.getCell().getItem() != null) {
                        player.addToInventory(player.getCell().getItem());
                        player.getCell().getItem().interact(player);
                        player.getCell().setItem(null);
                        refresh();
                    }
                    break;
                case S:
                    String save = "save";
                    Player savePlayer = map.getPlayer();
                    Modal saveModal = new Modal(savePlayer, dbManager, map);
                    saveModal.show(primaryStage, save);
                    break;
             /*   case L:
                    String load = "load";
                    Player loadPlayer = map.getPlayer();
                    Modal loadModal = new Modal();
                    loadModal.show(primaryStage, load);
                    //dbManager.loadPlayer(loadPlayer);
                    Player player = map.getPlayer();
                    PlayerModel saved = dbManager.savePlayer(player);
                    dbManager.saveGame(map, saved);
                    break;
                case L:
                    String mapStr = dbManager.loadMapStr(16);
                    InputStream loadFrom = new ByteArrayInputStream(mapStr.getBytes());
                    break;*/
                case E:
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Export save file");
                    a.setHeaderText("This feature is not working yet!\nSorry for the inconvenience.");
                    a.show();
                    break;
                case Q:
                    Alert b = new Alert(Alert.AlertType.INFORMATION);
                    b.setTitle("Game quit");
                    b.setHeaderText("Thanks for playing the demo! See you soon!");
                    b.showAndWait();
                    if (!b.isShowing()) System.exit(0);
            }
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + player.getHealth());
        armorLabel.setText("" + player.getArmor());
        damageLabel.setText("" + player.getDamage());
        //inventoryLabel.setText();
    }

    private void refreshActors() {
        map.getActors().removeIf(actor -> actor.getCell().getActor() == null);
        for (Actor actor : map.getActors()) {
            actor.act(0, 0);
        }
    }

    private boolean checkPlayerDead() {
        return player.getHealth() <= 0;
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("SQL not connected");
            a.setHeaderText("The game cannot connect to the database.\nEither there is none or the system variables are incorrect.");
            a.show();
        }
    }

}
