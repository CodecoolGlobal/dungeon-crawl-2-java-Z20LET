package com.codecool.dungeoncrawl.logic.modal;

import javafx.collections.FXCollections;
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

    final Button saveButton = new Button("Save");
    final Button cancelButton = new Button("Cancel");

    final Button selectButton = new Button("Select");


    String savedGameName = "";

    TextField textField;

    ComboBox combobox;

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
                savedGameName = textField.getText();
                dialogVbox.getChildren().addAll(new Label("Name: "), textField);
                break;
            case "load":
                dialogVbox.getChildren().add(new Text("Load your game! \n Choose a previous game state: \""));
                String[] testString = new String[]{"egyik mentés", "másik mentés"};
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
