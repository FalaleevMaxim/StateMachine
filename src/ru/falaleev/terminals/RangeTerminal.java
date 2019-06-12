package ru.falaleev.terminals;

import java.util.Objects;

/**
 * Терминал, проверяющий диапазон символов
 */
public class RangeTerminal extends Terminal {
    private char from;
    private char to;

    public RangeTerminal(String name, char from, char to) {
        super(name);
        this.from = from < to ? from : to;
        this.to = from < to ? to : from;

    }

    @Override
    public boolean match(char c) {
        return c >= from && c <= to;
    }

    public char getFrom() {
        return from;
    }

    public void setFrom(char from) {
        this.from = from;
    }

    public char getTo() {
        return to;
    }

    public void setTo(char to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeTerminal)) return false;
        if (!super.equals(o)) return false;
        RangeTerminal that = (RangeTerminal) o;
        return getFrom() == that.getFrom() &&
                getTo() == that.getTo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFrom(), getTo());
    }
}
