package ru.falaleev.core.alphabets;

import ru.falaleev.core.nonterminal.NonTerminal;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Алфавит нетерминалов
 */
public class NonTerminalAlphabet implements Serializable {
    private final Map<String, NonTerminal> nonterminals = new HashMap<>();

    public NonTerminalAlphabet() {
        for (NonTerminal nonTerminal : NonTerminal.BASIC_ALPHABET) {
            nonterminals.put(nonTerminal.getName(), nonTerminal);
        }
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
        if (NonTerminal.BASIC_ALPHABET.stream().anyMatch(nt -> nt.getName().equals(nonTerminalName)))
            throw new IllegalArgumentException("You can not remove basic nonterminals");
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
