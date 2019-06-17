package ru.falaleev.ui.forms.terminal;

import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.RangeTerminal;
import ru.falaleev.ui.widgets.terminal.fieldset.RangeFieldset;

public class RangeTerminalEditDialog extends AbstractTerminalEditDialog<RangeTerminal, RangeFieldset> {

    private static final CreateFromFieldset<RangeTerminal, RangeFieldset> CREATE_FROM_FIELDSET = (name, fieldset) -> new RangeTerminal(name, fieldset.getFrom(), fieldset.getTo());
    private static final EditFromFieldset<RangeTerminal, RangeFieldset> EDIT_FROM_FIELDSET = (terminal, fieldset) -> {
        terminal.setFrom(fieldset.getFrom());
        terminal.setTo(fieldset.getTo());
    };

    public RangeTerminalEditDialog(TerminalAlphabet alphabet, RangeTerminal item) {
        super(item, new RangeFieldset(item), alphabet, CREATE_FROM_FIELDSET, EDIT_FROM_FIELDSET);
    }
}

