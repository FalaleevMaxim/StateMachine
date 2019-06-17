package ru.falaleev.ui.widgets.common;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {
    private boolean hex;

    public NumberTextField() {
    }

    public NumberTextField(String text) {
        super(text);
        if (!validate(text)) {
            setText("");
        }
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    public boolean validate(String text) {
        return text.matches(hex ? "[0-9a-fA-F]*" : "[0-9]*");
    }

    public boolean isHex() {
        return hex;
    }

    public void setHex(boolean hex) {
        if (this.hex == hex) return;
        if (getText().isEmpty()) {
            this.hex = hex;
        } else {
            String text = getText();
            int code = Integer.parseInt(text, this.hex ? 16 : 10);
            String newText = Integer.toString(code, hex ? 16 : 10);
            this.hex = hex;
            setText(newText);
        }
    }

    public void setNumber(int number) {
        setText(Integer.toString(number, hex ? 16 : 10));
    }
}
