package com.codecool.dungeoncrawl.logic.modal;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Modal {

    Stage dialog = new Stage();

    Button saveButton = new Button("Save");

    Button cancelButton = new Button("Cancel");

    Button selectButton = new Button("Select");

    String savedGameName = "";

    TextField textField;

    ComboBox combobox;

    Player player;

    GameDatabaseManager gdb;

    GameMap map;

    public Modal(Player player, GameDatabaseManager gdb, GameMap map){
        this.player = player;
        this.gdb = gdb;
        this.map = map;
        this.saveButton.setOnAction(save);
        this.cancelButton.setOnAction(cancel);
        this.selectButton.setOnAction(select);
    }

    public String getSavedGameName() {
        return savedGameName;
    }

    EventHandler save = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println("Game Saved " + System.currentTimeMillis());
            savedGameName = textField.getText();
            player.setName(savedGameName);
            PlayerModel saved = gdb.savePlayer(player);
            gdb.saveGame(map, saved);
            // TODO dbManager.save()

        }
    };

    EventHandler select = new EventHandler() {
        @Override
        public void handle(Event event) {
            // TODO dbManager.load();
            dialog.close();
        }
    };

    EventHandler cancel = new EventHandler() {
        @Override
        public void handle(Event event) {
            dialog.close();
        }
    };


    public void show (Stage primaryStage, String task) {
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        switch (task){
            case "save":
                dialogVbox.getChildren().add(new Text("Save your game! "));
                dialogVbox.getChildren().add(saveButton);
                textField = new TextField();
                dialogVbox.getChildren().addAll(new Label("Name: "), textField);
                break;
            case "load":
                dialogVbox.getChildren().add(new Text("Load your game! \n Choose a previous game state: \""));
                String[] testString = new String[]{"egyik mentés időpont ", "másik mentés"}; // TODO get String list from DB
                combobox = new ComboBox<String>(FXCollections.observableArrayList(testString));
                combobox.getSelectionModel().select(0);
                combobox.setId("changed");
                dialogVbox.getChildren().add(combobox);
                dialogVbox.getChildren().add(selectButton);
                break;
        }
        dialogVbox.getChildren().add(cancelButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
