package ru.falaleev.ui.util;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

public class UiUtil {
    public static void alert(Throwable e) {
        alert(e.getMessage());
    }

    public static void alert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR, s, ButtonType.OK);
        alert.showAndWait();
    }

    public static HBox hCenter(Node node) {
        HBox hBox = new HBox(node);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        return hBox;
    }
}
