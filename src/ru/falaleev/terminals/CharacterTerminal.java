package ru.falaleev.terminals;

import java.util.Objects;

/**
 * Реализация терминала, проверяющая конкретный символ.
 */
public class CharacterTerminal extends Terminal {
    private char c;

    /**
     * Конструктор, принимающий символ. Имя терминала будет совпадать с символом.
     * Символ должен быть буквой или цифрой. Другие символы не могут быть в названии предиката, для них испорльзовать {@link #CharacterTerminal(String, char)}
     *
     * @param c Символ
     */
    public CharacterTerminal(char c) {
        super(String.valueOf(c));
        this.c = c;
    }

    /**
     * Принимает имя терминала и проверяемый им символ
     *
     * @param name Имя терминала
     * @param c    Проверяемый символ
     */
    public CharacterTerminal(String name, char c) {
        super(name);
        this.c = c;
    }

    @Override
    public final boolean match(char c) {
        return this.c == c;
    }

    public void setChar(char c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterTerminal)) return false;
        if (!super.equals(o)) return false;
        CharacterTerminal that = (CharacterTerminal) o;
        return c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), c);
    }
}
