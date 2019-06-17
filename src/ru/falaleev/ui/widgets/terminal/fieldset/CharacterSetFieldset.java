package ru.falaleev.ui.widgets.terminal.fieldset;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.terminals.SetTerminal;
import ru.falaleev.ui.forms.CharacterAddDialog;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.ui.widgets.terminal.CharacterItemWidget;

import java.util.HashSet;
import java.util.Set;

public class CharacterSetFieldset extends VBox {
    private Set<Character> characters = new HashSet<>();

    private VBox list;
    private ScrollPane scroll;
    private Label label;
    private Button addBtn;

    public CharacterSetFieldset(SetTerminal item) {
        setMinHeight(200);
        label = new Label("Коды символов:");

        list = new VBox();
        VBox.setVgrow(list, Priority.ALWAYS);
        scroll = new ScrollPane(list);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        setVgrow(scroll, Priority.ALWAYS);
        setStyle("-fx-border-width: 1px; -fx-border-color: grey");

        addBtn = new Button("Добавить");
        addBtn.setOnMouseClicked(event ->
                new CharacterAddDialog()
                        .showAndWait()
                        .ifPresent(this::addCharacter));


        getChildren().addAll(UiUtil.hCenter(label), scroll, UiUtil.hCenter(addBtn));

        if (item != null) {
            item.getChars().forEach(this::addCharacter);
        }
    }

    public void addCharacter(Character character) {
        if (characters.add(character)) {
            list.getChildren().add(new CharacterItemWidget(this, character));
        }
    }

    public void remove(CharacterItemWidget characterWidget) {
        characters.remove(characterWidget.getCharacter());
        list.getChildren().remove(characterWidget);
    }

    public Set<Character> getCharacters() {
        return characters;
    }
}