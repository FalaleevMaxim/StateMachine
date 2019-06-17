package ru.falaleev.core.machine;

import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.core.terminals.Terminal;

/**
 * Переход между состояниями конечного автомата
 */
public class StateMachineMove {
    public final NonTerminal from;
    public final char terminalChar;
    public final Terminal terminal;
    public final NonTerminal to;

    public StateMachineMove(NonTerminal from, char terminalChar, Terminal terminal, NonTerminal to) {
        if(from==null) throw new IllegalArgumentException("From can not be null");
        if(terminal!=null && to==null) to = NonTerminal.FAIL;
        this.from = from;
        this.terminalChar = terminalChar;
        this.terminal = terminal;
        this.to = to;
    }

    public StateMachineMove(NonTerminal from) {
        this(from, '\0', null, null);
    }

    @Override
    public String toString() {
        if(terminal==null) return "Start with "+from;
        return "From " + from + " by '" + terminalChar + "' (" + terminal + ") to " + to;
    }
}
