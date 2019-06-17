package ru.falaleev.ui.widgets.terminal.fieldset;

import javafx.scene.layout.VBox;
import ru.falaleev.core.terminals.RangeTerminal;

public class RangeFieldset extends VBox {
    private CharacterFieldset fromFieldset;
    private CharacterFieldset toFieldset;

    public RangeFieldset(RangeTerminal item) {
        this(item.getFrom(), item.getTo());
    }

    public RangeFieldset(char from, char to) {
        fromFieldset = new CharacterFieldset(from);
        toFieldset = new CharacterFieldset(to);

        getChildren().addAll(fromFieldset, toFieldset);
    }

    public RangeFieldset() {
        fromFieldset = new CharacterFieldset();
        toFieldset = new CharacterFieldset();

        getChildren().addAll(fromFieldset, toFieldset);
    }

    public char getFrom(){
        return fromFieldset.getChar();
    }

    public char getTo(){
        return toFieldset.getChar();
    }
}
