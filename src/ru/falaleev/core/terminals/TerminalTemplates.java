package ru.falaleev.core.terminals;

import java.util.function.Supplier;

/**
 * Класс, содержащий шаблоны часто используемых терминалов
 */
public final class TerminalTemplates {
    private TerminalTemplates() {
    }

    /**
     * Перенос строки
     */
    public static final Supplier<Terminal> NEWLINE = () -> new CharacterTerminal("NEWLINE", '\n');
    /**
     * Возврат каретки
     */
    public static final Supplier<Terminal> RETURN = () -> new CharacterTerminal("RETURN", '\r');

    /**
     * Символы, участвующие в переносе строки
     */
    public static final Supplier<Terminal> NEWLINES = () -> new SetTerminal("NEWLINES", '\n', '\r');

    /**
     * Пробел
     */
    public static final Supplier<Terminal> WHITESPACE = () -> new CharacterTerminal("WHITESPACE", ' ');

    /**
     * Пробел
     */
    public static final Supplier<Terminal> WHITESPACES = () -> new SetTerminal("WHITESPACES", ' ', '\t');

    /**
     * Возврат каретки
     */
    public static final Supplier<Terminal> UNDERLINE = () -> new CharacterTerminal("UNDERLINE", '_');

    /**
     * Цифра
     */
    public static final Supplier<Terminal> DIGIT = () -> new RangeTerminal("DIGIT", '0', '9');

    /**
     * Строчная буква латиницы
     */
    public static final Supplier<Terminal> LATIN_LOWER = () -> new RangeTerminal("LATIN_LOWER", 'a', 'z');

    /**
     * Занлавная буква латиницы
     */
    public static final Supplier<Terminal> LATIN_UPPER = () -> new RangeTerminal("LATIN_UPPER", 'A', 'Z');

    /**
     * Буква латиницы любого регистра
     */
    public static final Supplier<Terminal> LATIN = () -> new CombinedTerminal("LATIN", LATIN_LOWER.get(), LATIN_UPPER.get());

    /**
     * Строчная буква кириллицы.
     * Буквва ё не входит в диапазон а-я поэтому комбинирует диапазон с буквой ё.
     */
    public static final Supplier<Terminal> CYRILLIC_LOWER = () -> new CombinedTerminal("CYRILLIC_LOWER",
            new RangeTerminal("CYRILLIC_LOWER_NO_ё", 'а', 'я'),
            new CharacterTerminal('ё'));

    /**
     * Заглавная буква кириллицы.
     * Буквва Ё не входит в диапазон А-Я поэтому комбинирует диапазон с буквой Ё.
     */
    public static final Supplier<Terminal> CYRILLIC_UPPER = () -> new CombinedTerminal("CYRILLIC_UPPER",
            new RangeTerminal("CYRILLIC_UPPER_NO_Ё", 'А', 'Я'),
            new CharacterTerminal('Ё'));

    /**
     * Буква кириллицы любого регистра
     */
    public static final Supplier<Terminal> CYRILLIC = () -> new CombinedTerminal("CYRILLIC", CYRILLIC_LOWER.get(), CYRILLIC_UPPER.get());

    /**
     * Любая строчная буква
     */
    public static final Supplier<Terminal> LOWER = () -> new CombinedTerminal("LOWER", LATIN_LOWER.get(), CYRILLIC_LOWER.get());

    /**
     * Любая заглавная буква
     */
    public static final Supplier<Terminal> UPPER = () -> new CombinedTerminal("UPPER", LATIN_UPPER.get(), CYRILLIC_UPPER.get());
}
