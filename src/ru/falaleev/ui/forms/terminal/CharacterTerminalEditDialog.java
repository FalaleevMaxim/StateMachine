package ru.falaleev.ui.forms.terminal;

import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.CharacterTerminal;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterFieldset;

public class CharacterTerminalEditDialog extends AbstractTerminalEditDialog<CharacterTerminal, CharacterFieldset> {

    private static final CreateFromFieldset<CharacterTerminal, CharacterFieldset> CREATE_FROM_FIELDSET = (name, fieldset) -> new CharacterTerminal(name, fieldset.getChar());
    private static final EditFromFieldset<CharacterTerminal, CharacterFieldset> EDIT_FROM_FIELDSET = (terminal, fieldset) -> terminal.setChar(fieldset.getChar());

    public CharacterTerminalEditDialog(TerminalAlphabet alphabet, CharacterTerminal item) {
        super(item, new CharacterFieldset(item), alphabet, CREATE_FROM_FIELDSET, EDIT_FROM_FIELDSET);
    }
}
