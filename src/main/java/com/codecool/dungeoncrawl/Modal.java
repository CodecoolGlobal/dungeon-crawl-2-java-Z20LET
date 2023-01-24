package com.codecool.dungeoncrawl;


import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.VBox;
        import javafx.stage.Modality;
        import javafx.stage.Stage;

public class Modal extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Create the primary stage
        primaryStage.setTitle("Modal Example");

        // Create the text field
        TextField nameInput = new TextField();

        // Create the buttons
        Button btn1 = new Button("OK");
        Button btn2 = new Button("Cancel");

        // Create the secondary Stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Name Input Dialog");

        // Create the label
        Label label = new Label("Please Enter your Name");

        // Create the VBox
        VBox vbox = new VBox(label, nameInput, btn1, btn2);

        // Set the scene
        Scene scene = new Scene(vbox);

        // Set the scene and show the dialog
        dialog.setScene(scene);
        dialog.show();
    }
}