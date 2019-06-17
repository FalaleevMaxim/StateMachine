package ru.falaleev.ui.widgets.terminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterSetFieldset;

public class CharacterItemWidget extends BorderPane {
    private CharacterSetFieldset list;
    private Character character;
    private Label textLabel;
    private Button removeBtn;

    public CharacterItemWidget(CharacterSetFieldset list, Character character) {
        this.list = list;
        this.character = character;
        textLabel = new Label();
        textLabel.setAlignment(Pos.BASELINE_LEFT);

        String text = Integer.toString(character);
        if(Character.isBmpCodePoint(character)) text+= " ('"+character+"')";
        textLabel.setText(text);
        setStyle("-fx-border-width: 1px; -fx-border-color: black");

        removeBtn = new Button("Удалить");
        removeBtn.setOnMouseClicked(event -> this.list.remove(this));

        setLeft(textLabel);
        setRight(removeBtn);
    }

    public Character getCharacter() {
        return character;
    }
}
