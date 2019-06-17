package ru.falaleev.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.falaleev.ui.forms.MainForm;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root = new MainForm();
        primaryStage.setTitle("State machine builder");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
