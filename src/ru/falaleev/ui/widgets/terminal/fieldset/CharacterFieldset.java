package ru.falaleev.ui.widgets.terminal.fieldset;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ru.falaleev.core.terminals.CharacterTerminal;
import ru.falaleev.ui.widgets.common.NumberTextField;
import ru.falaleev.ui.widgets.common.SingleCharTextField;

public class CharacterFieldset extends HBox {
    private NumberTextField charCode;
    private SingleCharTextField character;
    private CheckBox hex;

    public CharacterFieldset(char c) {
        this();
        charCode.setText(Integer.toString(c));
    }

    public CharacterFieldset() {
        setSpacing(5);
        character = new SingleCharTextField();
        charCode = new NumberTextField();
        charCode.setMaxWidth(80);
        charCode.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String text = String.valueOf(getChar());
                if (character.validate(text)) {
                    character.setText(text);
                } else {
                    character.setText("");
                }
            } catch (RuntimeException ignored) {
            }
        });
        hex = new CheckBox("Шестнадцатиричный");
        hex.selectedProperty().addListener((observable, oldValue, newValue) -> {
            charCode.setHex(newValue);
        });
        character.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                charCode.clear();
            } else {
                charCode.setNumber(newValue.charAt(0));
            }
        });
        getChildren().addAll(new Label("Символ: "), character, new Label(" Код: "), charCode, hex);
    }

    public CharacterFieldset(CharacterTerminal terminal) {
        this();
        if (terminal != null) {
            charCode.setText(Integer.toString(Character.getNumericValue(terminal.getChar())));
        }
    }

    public Character getChar() {
        String text = charCode.getText();
        if (text.isEmpty()) throw new IllegalStateException("Character code is empty!");
        boolean hex = charCode.isHex();
        int code = Integer.parseInt(text, hex ? 16 : 10);
        if (code > Character.MAX_VALUE)
            throw new IllegalStateException("Code is too big to be character. Max is" + (hex ? "FFFF" : "1114111"));
        return (char) code;
    }
}
