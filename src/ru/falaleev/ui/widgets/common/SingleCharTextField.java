package ru.falaleev.ui.widgets.common;

import javafx.scene.control.TextField;

public class SingleCharTextField extends TextField {
    {
        setMaxWidth(30);
        setOnKeyTyped(event -> {
            event.consume();
            String character = event.getCharacter();
            if(validate(character)) setText(character);
            else setText("");
        });
    }
    public SingleCharTextField() {

    }

    public SingleCharTextField(String text) {
        super(text);
        if(!validate(text)){
            setText("");
        }
    }

    /*@Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }*/

    public boolean validate(String text)
    {
        if(text.isEmpty()) return true;
        if(text.length()>1) return false;
        return Character.isBmpCodePoint(text.charAt(0));
    }
}
