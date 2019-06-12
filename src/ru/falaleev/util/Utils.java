package ru.falaleev.util;

import java.util.function.Predicate;

public final class Utils {
    public static void checkName(String name) {
        //Проверка что имя не пустое
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name can not be empty");
        //Проверка что имя состоит только из букв, цифр и символа подчёркивания
        boolean validName = name.chars()
                .mapToObj(i -> (char) i)
                .allMatch(((Predicate<Character>) Character::isLetterOrDigit).or(c -> c.equals('_')));
        if (!validName) {
            throw new IllegalArgumentException("Name must contain only letters or digits");
        }
    }
}
