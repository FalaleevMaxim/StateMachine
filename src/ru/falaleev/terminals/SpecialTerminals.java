package ru.falaleev.terminals;

/**
 * Класс, содержащий константы с особыми терминалами
 */
public final class SpecialTerminals {
    private SpecialTerminals() {
    }

    /**
     * Перенос строки
     */
    public static final Terminal NEWLINE = new CharacterTerminal("NEWLINE", '\n');
    /**
     * Возврат каретки
     */
    public static final Terminal RETURN = new CharacterTerminal("RETURN", '\r');

    /**
     * Возврат каретки
     */
    public static final Terminal UNDERLINE = new CharacterTerminal("RETURN", '\r');

    /**
     * Цифра
     */
    public static final Terminal DIGIT = new RangeTerminal("DIGIT", '0', '9');

    /**
     * Строчная буква латиницы
     */
    public static final Terminal LATIN_LOWER = new RangeTerminal("LATIN_LOWER", 'a', 'z');

    /**
     * Занлавная буква латиницы
     */
    public static final Terminal LATIN_UPPER = new RangeTerminal("LATIN_UPPER", 'A', 'Z');

    /**
     * Буква латиницы любого регистра
     */
    public static final Terminal LATIN = new CombinedTerminal("LATIN", LATIN_LOWER, LATIN_UPPER);

    /**
     * Строчная буква кириллицы.
     * Буквва ё не входит в диапазон а-я поэтому комбинирует диапазон с буквой ё.
     */
    public static final Terminal CYRILLIC_LOWER = new CombinedTerminal("CYRILLIC_LOWER",
            new RangeTerminal("CYRILLIC_LOWER_NO_ё", 'а', 'я'),
            new CharacterTerminal('ё'));

    /**
     * Заглавная буква кириллицы.
     * Буквва Ё не входит в диапазон А-Я поэтому комбинирует диапазон с буквой Ё.
     */
    public static final Terminal CYRILLIC_UPPER = new CombinedTerminal("CYRILLIC_UPPER",
            new RangeTerminal("CYRILLIC_UPPER_NO_Ё", 'А', 'Я'),
            new CharacterTerminal('Ё'));

    /**
     * Буква кириллицы любого регистра
     */
    public static final Terminal CYRILLIC = new CombinedTerminal("CYRILLIC", CYRILLIC_LOWER, CYRILLIC_UPPER);

    /**
     * Любая строчная буква
     */
    public static final Terminal LOWER = new CombinedTerminal("LOWER", LATIN_LOWER, CYRILLIC_LOWER);

    /**
     * Любая заглавная буква
     */
    public static final Terminal UPPER = new CombinedTerminal("UPPER", LATIN_UPPER, CYRILLIC_UPPER);
}
