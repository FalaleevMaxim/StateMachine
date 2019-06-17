package ru.falaleev.ui.forms;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.falaleev.ui.util.UiUtil;

import java.util.List;

public class SelectDialog<T> extends Dialog<T> {
    ListView<T> list;
    T selected;

    public SelectDialog(List<T> items) {
        list = new ListView<>(FXCollections.observableList(items));
        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        Button close = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        close.setText("Отмена");

        Button saveBtn = new Button("Выбрать");
        saveBtn.setOnMouseClicked(event -> {
            selected = list.getFocusModel().getFocusedItem();
            setResult(selected);
        });

        setOnCloseRequest(event -> setResult(selected));

        VBox content = new VBox(list, UiUtil.hCenter(saveBtn));
        getDialogPane().setContent(content);
    }
}
