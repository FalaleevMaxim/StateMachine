package ru.falaleev.terminals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Терминал, проверяющий символ из множества
 */
public class SetTerminal extends Terminal {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetTerminal)) return false;
        if (!super.equals(o)) return false;
        SetTerminal that = (SetTerminal) o;
        return getChars().equals(that.getChars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChars());
    }
}
