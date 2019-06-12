package ru.falaleev.terminals;

import ru.falaleev.util.Utils;

/**
 * Базовый класс тертинала. Имеет имя и метод проверки, является ли символ этим терминалом
 */
public abstract class Terminal {
    private String name;

    public Terminal(String name) {
        setName(name);
    }

    public abstract boolean match(char c);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Utils.checkName(name);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Terminal)) return false;
        Terminal terminal = (Terminal) o;
        return getName().equals(terminal.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
