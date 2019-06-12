package ru.falaleev.terminals;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Комбинарует несколько терминалов.
 * Проверяет что символ соответствует хотя бы одному из терминалов.
 */
public class CombinedTerminal extends Terminal {
    private final List<Terminal> combined;

    public CombinedTerminal(String name, Terminal... combined) {
        super(name);
        this.combined = Arrays.asList(combined);
    }

    @Override
    public boolean match(char c) {
        return combined.stream().anyMatch(terminal -> terminal.match(c));
    }

    public List<Terminal> getCombinedTerminals() {
        return combined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombinedTerminal)) return false;
        if (!super.equals(o)) return false;
        CombinedTerminal that = (CombinedTerminal) o;
        return combined.equals(that.combined);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), combined);
    }
}
