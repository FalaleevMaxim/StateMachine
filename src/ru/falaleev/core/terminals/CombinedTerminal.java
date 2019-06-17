package ru.falaleev.core.terminals;

import java.io.Serializable;
import java.util.*;

/**
 * Комбинарует несколько терминалов.
 * Проверяет что символ соответствует хотя бы одному из терминалов.
 */
public class CombinedTerminal extends Terminal implements Serializable {
    private final List<Terminal> combined;

    public CombinedTerminal(String name, Collection<Terminal> combined) {
        super(name);
        this.combined = new ArrayList<>(combined);
    }

    public CombinedTerminal(String name, Terminal... combined) {
        this(name, Arrays.asList(combined));
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
}
