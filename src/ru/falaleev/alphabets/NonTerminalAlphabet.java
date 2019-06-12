package ru.falaleev.alphabets;

import ru.falaleev.nonterminal.NonTerminal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Алфавит нетерминалов
 */
public class NonTerminalAlphabet {
    private final Map<String, NonTerminal> nonterminals = new HashMap<>();

    /**
     * Изначально содердит стартовый нетерминал S
     */
    public NonTerminalAlphabet() {
        nonterminals.put("S", NonTerminal.S);
    }

    public Map<String, NonTerminal> getNonterminals() {
        return Collections.unmodifiableMap(nonterminals);
    }

    public void add(NonTerminal nonTerminal) {
        if (nonterminals.containsKey(nonTerminal.getName())) {
            throw new IllegalStateException("Nonterminal with name '" + nonTerminal.getName() + "' already exists");
        }
        nonterminals.put(nonTerminal.getName(), nonTerminal);
    }

    public void remove(String nonTerminalName) {
        //Нельзя удалить стартовый нетерминал S
        if ("S".equals(nonTerminalName)) throw new IllegalArgumentException("You can not remove start nonterminal S");
        nonterminals.remove(nonTerminalName);
    }

    public boolean contains(NonTerminal nonTerminal) {
        return nonterminals.containsValue(nonTerminal);
    }

    public boolean contains(String nonTerminalName) {
        return nonterminals.containsKey(nonTerminalName);
    }

    public NonTerminal get(String nonTerminalName) {
        return nonterminals.get(nonTerminalName);
    }
}
