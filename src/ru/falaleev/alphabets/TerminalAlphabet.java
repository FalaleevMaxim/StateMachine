package ru.falaleev.alphabets;

import ru.falaleev.terminals.Terminal;

import java.util.*;

/**
 * Алфавит терминалов
 */
public class TerminalAlphabet {
    private final Map<String, Terminal> terminals = new HashMap<>();

    public TerminalAlphabet(Terminal... terminals) {
        this(Arrays.asList(terminals));
    }

    public TerminalAlphabet(List<Terminal> terminals) {
        for (Terminal terminal : terminals) {
            this.terminals.put(terminal.getName(), terminal);
        }
    }

    public Map<String, Terminal> getTerminals() {
        return Collections.unmodifiableMap(terminals);
    }

    public void remove(String terminalName) {
        terminals.remove(terminalName);
    }

    public void add(Terminal terminal) {
        if (terminals.containsKey(terminal.getName()))
            throw new IllegalStateException("Terminal with name '" + terminal.getName() + "' already exists");
        terminals.put(terminal.getName(), terminal);
    }

    public boolean contains(String terminalName) {
        return terminals.containsKey(terminalName);
    }

    public boolean contains(Terminal terminal) {
        return terminals.containsValue(terminal);
    }

    public Terminal get(String terminalName) {
        return terminals.get(terminalName);
    }

    @Override
    public String toString() {
        return terminals.values().toString();
    }
}
