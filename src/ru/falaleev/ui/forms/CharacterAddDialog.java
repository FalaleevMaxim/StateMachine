package ru.falaleev.ui.forms;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterFieldset;

public class CharacterAddDialog extends Dialog<Character> {
    private CharacterFieldset fieldset;
    private Character item;

    private Button saveBtn;

    public CharacterAddDialog() {
        fieldset = new CharacterFieldset();

        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Button closeBtn = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        closeBtn.setText("Отмена");

        saveBtn = new Button("Сохранить");

        setOnCloseRequest(event -> setResult(this.item));

        saveBtn.setOnMouseClicked(event -> {
            try {
                item = fieldset.getChar();
                close();
            } catch (RuntimeException e) {
                UiUtil.alert(e);
            }
        });

        VBox content = new VBox(fieldset, UiUtil.hCenter(saveBtn));
        content.setSpacing(5);
        getDialogPane().setContent(content);
    }
}
