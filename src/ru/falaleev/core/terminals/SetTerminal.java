package ru.falaleev.core.terminals;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Терминал, проверяющий символ из множества
 */
public class SetTerminal extends Terminal implements Serializable {
    private final Set<Character> chars;

    public SetTerminal(String name, Character... chars) {
        super(name);
        this.chars = new HashSet<>(Arrays.asList(chars));
    }

    public SetTerminal(String name, Set<Character> chars) {
        super(name);
        this.chars = new HashSet<>(chars);
    }

    @Override
    public boolean match(char c) {
        return chars.contains(c);
    }

    public Set<Character> getChars() {
        return chars;
    }
}
