package ru.falaleev.core.terminals;

import ru.falaleev.util.Utils;

import java.io.Serializable;

/**
 * Базовый класс тертинала. Имеет имя и метод проверки, является ли символ этим терминалом
 */
public abstract class Terminal implements Serializable {
    private String name;

    public Terminal(String name) {
        setName(name);
    }

    public abstract boolean match(char c);

    public String getName() {
        return name;
    }

    /**
     * Небезопасно! Поле участвует в equals и hashCode.
     * Удалить терминал из алфавита перед изменением этого поля!
     */
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
