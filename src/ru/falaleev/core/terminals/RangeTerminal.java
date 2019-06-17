package ru.falaleev.core.terminals;

import java.io.Serializable;

/**
 * Терминал, проверяющий диапазон символов
 */
public class RangeTerminal extends Terminal implements Serializable {
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
}
