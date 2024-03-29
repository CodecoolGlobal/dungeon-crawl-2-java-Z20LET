package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.SkullPlayer;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.modal.Modal;
import com.codecool.dungeoncrawl.model.GameState;
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
    GameMap map = MapLoader.loadMap(null);
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
    static int gameIndex;
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
        ui.add(new Label("S - Save to SQL"), 0, 35);
        ui.add(new Label("E - Export JSON"), 0, 40);
        ui.add(new Label("C - Choose save"), 0, 45);
        ui.add(new Label("L - Load save"), 0, 50);
        ui.add(new Label("Q - Quit game"), 0, 55);
        ui.add(new Label("DEMO version"), 0, 60);


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
                map = MapLoader.loadMap(null);
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
                case C:
                    String load = "load";
                    Player loadPlayer = map.getPlayer();
                    Modal loadModal = new Modal(loadPlayer, dbManager, map);
                    loadModal.show(primaryStage, load);
                    refresh();
                    break;
                case L:
                    GameState game = dbManager.loadGame(gameIndex);
                    InputStream loadFrom = new ByteArrayInputStream(game.getCurrentMap().getBytes());
                    map = MapLoader.loadMap(loadFrom);
                    PlayerModel playerLoaded = game.getPlayer();
                    player = new Player(map.getPlayer().getCell());
                    player.setName(playerLoaded.getPlayerName());
                    player.setHealth(playerLoaded.getHp());
                    player.setArmor(playerLoaded.getArmor());
                    player.setDamage(playerLoaded.getDamage());
                    refresh();
                    break;
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

    public static void setGameIndex(int selectedIndex) {
        gameIndex = selectedIndex;
    }
}
