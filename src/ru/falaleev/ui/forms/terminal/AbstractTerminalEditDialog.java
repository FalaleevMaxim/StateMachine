package ru.falaleev.ui.forms.terminal;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.util.Utils;

public abstract class AbstractTerminalEditDialog<T extends Terminal, F extends Pane> extends Dialog<T> {
    private T item;

    private F fieldset;

    private TextField nameField;


    public AbstractTerminalEditDialog(T item, F fieldset, TerminalAlphabet alphabet, CreateFromFieldset<T, F> createFunction, EditFromFieldset<T, F> editFunction) {
        this.item = item;
        this.fieldset = fieldset;

        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Button closeBtn = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        closeBtn.setText("Отмена");
        setOnCloseRequest(event -> setResult(this.item));

        nameField = new TextField(item.getName());
        HBox nameBox = new HBox(new Label("Название: "), nameField);

        Button saveBtn = new Button("Сохранить");
        saveBtn.setOnMouseClicked(event -> {
            try {
                String name = nameField.getText();
                Utils.checkName(name);
                if (!name.equals(item.getName())) {
                    if (alphabet.contains(name)) {
                        UiUtil.alert("Терминал с таким именем уже существует!");
                        return;
                    }
                    this.item = createFunction.createFromFieldset(name, fieldset);
                } else {
                    editFunction.editFromFieldset(item, fieldset);
                    this.item = null;
                }
                close();
            } catch (RuntimeException e) {
                UiUtil.alert(e);
            }
        });

        VBox content = new VBox(nameBox, fieldset, UiUtil.hCenter(saveBtn));
        content.setSpacing(5);
        getDialogPane().setContent(content);
    }

    public interface CreateFromFieldset<T extends Terminal, F extends Pane> {
        T createFromFieldset(String name, F fieldset);
    }

    public interface EditFromFieldset<T extends Terminal, F extends Pane> {
        void editFromFieldset(T terminal, F fieldset);
    }
}
