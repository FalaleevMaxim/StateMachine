package ru.falaleev.core.grammar;

import ru.falaleev.core.nonterminal.NonTerminal;

import java.io.Serializable;
import java.util.Objects;

/**
 * Правило вида A->aB, где S и B - нетерминалы, a - терминал
 */
public class Rule implements Serializable {
    /**
     * Имя нетерминала в левой стороне правила
     */
    private String left;
    /**
     * Имя терминала в правой сторон правила
     */
    private String terminal;
    /**
     * Имя нетерминала в провой стороне правила
     */
    private String right;

    public Rule(String left, String terminal, String right) {
        if (left == null) throw new IllegalArgumentException("Left part of rule is null");
        if (terminal == null) throw new IllegalArgumentException("Terminal of rule is null");
        if (right == null) right = NonTerminal.FINAL.getName();
        this.left = left;
        this.terminal = terminal;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getRight() {
        return right;
    }

    /**
     * Небезопасно! Поле участвует в equals и hashCode.
     * Удалить правило из грамматики перед изменением этого поля!
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * Небезопасно! Поле участвует в equals и hashCode.
     * Удалить правило из грамматики перед изменением этого поля!
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setRight(String right) {
        this.right = right;
    }

    /**
     * equals() и hashCode() учитывают только левую часть и терминал и не учитывают правый нетерминал.
     * Таким образом в множество правил нельзя будет добавить несколько правил, различающихся только правым нетерминалом.
     * Если добавлять такие правила, автомат должен быть недетерминированным, а это намного сложнее.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule)) return false;
        Rule rule = (Rule) o;
        return getLeft().equals(rule.getLeft()) &&
                getTerminal().equals(rule.getTerminal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getTerminal());
    }

    @Override
    public String toString() {
        return '[' + left + ']' + "->" + terminal + '[' + right + ']';
    }
}
