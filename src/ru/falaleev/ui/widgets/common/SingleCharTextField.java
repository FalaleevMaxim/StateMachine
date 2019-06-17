package ru.falaleev.ui.widgets.common;

import javafx.scene.control.TextField;

public class SingleCharTextField extends TextField {
    {
        setMaxWidth(30);
        setOnKeyTyped(event -> {
            event.consume();
            String character = event.getCharacter();
            if (validate(character)) setText(character);
            else setText("");
        });
    }

    public SingleCharTextField() {

    }

    public SingleCharTextField(String text) {
        super(text);
        if (!validate(text)) {
            setText("");
        }
    }

    public boolean validate(String text) {
        if (text.isEmpty()) return true;
        if (text.length() > 1) return false;
        return Character.isBmpCodePoint(text.charAt(0));
    }
}
