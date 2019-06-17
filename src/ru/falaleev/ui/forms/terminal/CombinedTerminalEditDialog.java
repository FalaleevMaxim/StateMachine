package ru.falaleev.ui.forms.terminal;

import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.CombinedTerminal;
import ru.falaleev.ui.widgets.terminal.fieldset.CombinedFieldset;

import java.util.ArrayList;

public class CombinedTerminalEditDialog extends AbstractTerminalEditDialog<CombinedTerminal, CombinedFieldset> {

    private static final CreateFromFieldset<CombinedTerminal, CombinedFieldset> CREATE_FROM_FIELDSET = (name, fieldset) -> new CombinedTerminal(name, new ArrayList<>(fieldset.getCombined()));
    private static final EditFromFieldset<CombinedTerminal, CombinedFieldset> EDIT_FROM_FIELDSET = (terminal, fieldset) -> {
        terminal.getCombinedTerminals().clear();
        terminal.getCombinedTerminals().addAll(fieldset.getCombined());
    };

    public CombinedTerminalEditDialog(TerminalAlphabet alphabet, CombinedTerminal item) {
        super(item, new CombinedFieldset(alphabet, item), alphabet, CREATE_FROM_FIELDSET, EDIT_FROM_FIELDSET);
    }
}
