package ru.falaleev.core.grammar;

import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.nonterminal.NonTerminal;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Грамматика. Содержит алфавит терминалов и нетерминалов и множество правил.
 */
public final class Grammar implements Serializable {
    /**
     * Алфавит нетерминалов
     */
    private NonTerminalAlphabet nonTerminalAlphabet = new NonTerminalAlphabet();
    /**
     * Алфавит терминалов
     */
    private TerminalAlphabet terminalAlphabet = new TerminalAlphabet();

    private Set<Rule> rules = new HashSet<>();

    public NonTerminalAlphabet getNonTerminalAlphabet() {
        return nonTerminalAlphabet;
    }

    public TerminalAlphabet getTerminalAlphabet() {
        return terminalAlphabet;
    }

    public Set<Rule> getRules() {
        return Collections.unmodifiableSet(rules);
    }

    public void addRule(Rule rule) {
        //Нельзя добавить правило, если есть правило с таким же нетерминалом слава и терминалом справа
        if (rules.contains(rule)) {
            String ruleProto = rule.getLeft() + "->" + rule.getTerminal() + "_";
            throw new IllegalArgumentException("Grammar already contains rule like " + ruleProto);
        }
        checkRulePartsInAlphabets(rule);
        rules.add(rule);
    }

    /**
     * Удаляет правило по имени нетерминала слева и терминала справа
     *
     * @param left     Левый нетерминал
     * @param terminal Терминал
     */
    public void removeRule(String left, String terminal) {
        rules.removeIf(rule -> rule.getLeft().equals(left) && rule.getTerminal().equals(terminal));
    }

    public void removeRule(Rule rule) {
        rules.remove(rule);
    }

    /**
     * Получает правило по имени нетерминала слева и терминала справа
     *
     * @param left     Левый нетерминал
     * @param terminal Терминал
     */
    public Rule getRule(String left, String terminal) {
        return rules.stream()
                .filter(rule -> rule.getLeft().equals(left) && rule.getTerminal().equals(terminal))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает все правила по имени левого нетерминала
     *
     * @param left Левый нетерминал
     */
    public Set<Rule> getRules(String left) {
        return rules.stream()
                .filter(rule -> rule.getLeft().equals(left))
                .collect(Collectors.toSet());
    }

    /**
     * Проверяет грамматику на правильность
     */
    public void isValid() {
        for (Rule rule : rules) {
            checkRulePartsInAlphabets(rule);
        }
    }

    private void checkRulePartsInAlphabets(Rule rule) {
        if (!nonTerminalAlphabet.contains(rule.getLeft()))
            throw new IllegalArgumentException("Nonterminal " + rule.getLeft() + " does not exist");
        if (!terminalAlphabet.contains(rule.getTerminal()))
            throw new IllegalArgumentException("Terminal " + rule.getTerminal() + " does not exist");
        if (!nonTerminalAlphabet.contains(rule.getRight())) {
            if (rule.getRight().equals(NonTerminal.FINAL.getName())) {
                nonTerminalAlphabet.add(NonTerminal.FINAL);
            } else {
                throw new IllegalArgumentException("Nonterminal " + rule.getRight() + " does not exist");
            }
        }
    }
}
