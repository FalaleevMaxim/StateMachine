package ru.falaleev.core.nonterminal;

import ru.falaleev.util.Utils;

import java.io.Serializable;
import java.util.*;

public class NonTerminal implements Serializable {
    public static final NonTerminal S = new NonTerminal("S");
    public static final NonTerminal FAIL = new NonTerminal("Fail");
    public static final NonTerminal FINAL = new NonTerminal("Final", true);

    public static final Set<NonTerminal> BASIC_ALPHABET = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(S, FAIL, FINAL)));

    public NonTerminal(String name) {
        this.name = name;
    }

    public NonTerminal(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    /**
     * Имя нетерминала
     */
    private String name;

    /**
     * Является ли финальным (принадлежит ли цепочка языку если автомат остановился на этом нетерминале)
     */
    private boolean isFinal;

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        if (aFinal && isFail()) throw new IllegalStateException("NonTerminal 'Fail' must not be final");
        if (aFinal && FINAL.getName().equals(name))
            throw new IllegalStateException("NonTerminal 'Final' must be final");
        isFinal = aFinal;
    }

    /**
     * Является ли стартовым нетерминалом S.
     */
    public boolean isStart() {
        return name.equals("S");
    }

    /**
     * Является ли стоком Fail.
     */
    public boolean isFail() {
        return name.equals("Fail");
    }

    public String getName() {
        return name;
    }

    /**
     * Небезопасно! Поле участвует в equals и hashCode.
     * Удалить нетерминал из алфавита перед изменением этого поля!
     */
    public void setName(String name) {
        Utils.checkName(name);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NonTerminal)) return false;
        NonTerminal that = (NonTerminal) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        String str = name;
        if (isFinal) str += "(!)";
        return str;
    }
}
