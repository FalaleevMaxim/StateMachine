package ru.falaleev.ui.forms.terminal;

import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.SetTerminal;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterSetFieldset;

public class SetTerminalEditDialog extends AbstractTerminalEditDialog<SetTerminal, CharacterSetFieldset> {

    private static final CreateFromFieldset<SetTerminal, CharacterSetFieldset> CREATE_FROM_FIELDSET = (name, fieldset) -> new SetTerminal(name, fieldset.getCharacters());
    private static final EditFromFieldset<SetTerminal, CharacterSetFieldset> EDIT_FROM_FIELDSET = (terminal, fieldset) -> {
        terminal.getChars().clear();
        terminal.getChars().addAll(fieldset.getCharacters());
    };

    public SetTerminalEditDialog(TerminalAlphabet alphabet, SetTerminal item) {
        super(item, new CharacterSetFieldset(item), alphabet, CREATE_FROM_FIELDSET, EDIT_FROM_FIELDSET);
    }
}
