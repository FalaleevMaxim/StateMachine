package ru.falaleev.nonterminal;

import ru.falaleev.util.Utils;

public class NonTerminal {
    public static final NonTerminal S = new NonTerminal("S");
    public static final NonTerminal FAIL = new NonTerminal("Fail");
    public static final NonTerminal FINAL = new NonTerminal("Final", true);

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

    /**
     * Является ли стартовым нетерминалом S.
     */
    public boolean isStart() {
        return name.equals("S");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Utils.checkName(name);
        this.name = name;
    }

    @Override
    public String toString() {
        String str = name;
        if (isFinal) str += '!';
        return str;
    }
}
