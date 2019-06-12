package ru.falaleev.machine;

import ru.falaleev.alphabets.NonTerminalAlphabet;
import ru.falaleev.alphabets.TerminalAlphabet;
import ru.falaleev.grammar.Grammar;
import ru.falaleev.grammar.Rule;
import ru.falaleev.nonterminal.NonTerminal;
import ru.falaleev.terminals.Terminal;

import java.util.*;

/**
 * Конечный автомат
 */
public class StateMachine {
    /**
     * Таблица переходов автомата в виде:
     * текущее состояние (левый нетерминал) -> терминал -> новое состояние (правый нетерминал).
     */
    private final Map<NonTerminal, Map<Terminal, NonTerminal>> rules = new HashMap<>();

    public StateMachine(Grammar grammar) {
        grammar.isValid();
        NonTerminalAlphabet nonTerminalAlphabet = grammar.getNonTerminalAlphabet();
        TerminalAlphabet terminalAlphabet = grammar.getTerminalAlphabet();

        //Сбор всех использованных в правилах терминальных и нетерминальных символов
        Set<NonTerminal> usedNonterminals = new HashSet<>();
        Set<Terminal> usedTerminals = new HashSet<>();
        for (Rule rule : grammar.getRules()) {
            usedNonterminals.add(nonTerminalAlphabet.get(rule.getLeft()));
            usedTerminals.add(terminalAlphabet.get(rule.getTerminal()));
            usedNonterminals.add(nonTerminalAlphabet.get(rule.getRight()));
        }

        //Ошибка если стартовый нетерминал S не используется ни в одном правиле
        if (!usedNonterminals.contains(NonTerminal.S))
            throw new IllegalStateException("Rules do not use start nonterminal S");
        //Ошибка если в правилах не используется ни один финальный нетерминал.
        if (usedNonterminals.stream().noneMatch(NonTerminal::isFinal))
            throw new IllegalStateException("No final nonterminal used in rules");

        //Формирование таблицы переходов автомата
        boolean usedS = false;
        boolean finishes = false;
        for (NonTerminal left : usedNonterminals) {
            if (left == NonTerminal.S) usedS = true;
            Map<Terminal, NonTerminal> ruleRight = new HashMap<>();
            rules.put(left, ruleRight);
            for (Terminal terminal : usedTerminals) {
                Rule rule = grammar.getRule(left.getName(), terminal.getName());
                NonTerminal nextState;
                if (rule == null) nextState = NonTerminal.FAIL;
                else nextState = nonTerminalAlphabet.get(rule.getRight());
                if (nextState.isFinal()) finishes = true;
                ruleRight.put(terminal, nextState);
            }
        }

        //Ошибка если ни одно правило не начиналось с S
        if (!usedS) throw new IllegalStateException("No rule starts from S");
        //Ошибка если ни одно правило не заканчивается на финальный нетерминал
        if (!finishes) throw new IllegalStateException("No rule leads to final nonterminal");

        //Все исходящие из FAIL переходы ведут в FAIL.
        usedNonterminals.add(NonTerminal.FAIL);
        Map<Terminal, NonTerminal> rulesFromFail = rules.get(NonTerminal.FAIL);
        if (rulesFromFail == null) {
            rulesFromFail = new HashMap<>();
            rules.put(NonTerminal.FAIL, rulesFromFail);
        }

        for (Terminal terminal : usedTerminals) {
            rulesFromFail.put(terminal, NonTerminal.FAIL);
        }
    }

    /**
     * Прогоняет автомат по строке и возвращает список пройденных состояний.
     *
     * @param s Строка, проверяемая автоматом
     * @return Список пройденных состояний
     */
    public List<NonTerminal> process(String s) {
        List<NonTerminal> states = new ArrayList<>(s.length());

        //Текущее состояние
        NonTerminal current = NonTerminal.S;
        states.add(current);

        //Цикл по символам строки
        for (char c : s.toCharArray()) {
            Map<Terminal, NonTerminal> rulesForCurrentState = rules.get(current);
            current = rulesForCurrentState.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().match(c)) //Поиск терминала, под который подходит символ
                    .map(Map.Entry::getValue) //Получение нетерминала из правила, соответствующего найденному нетерминалу
                    .findFirst()
                    .orElse(NonTerminal.FAIL); //Если символ не подошёл ни одному терминалу, то FAIL.
            states.add(current);
        }

        return states;
    }

    /**
     * Проверяет принадлежность строки языку.
     * Прогоняет строку по автомату и проверяет, что последнее состояние финальное.
     *
     * @param str проверяемая строка
     */
    public boolean check(String str) {
        List<NonTerminal> states = process(str);
        return isLastFinal(states);
    }

    /**
     * Проверяет, является ли последнее состояние финальным
     *
     * @param states список состояний, пройденных автоматом
     */
    public boolean isLastFinal(List<NonTerminal> states) {
        return states.get(states.size() - 1).isFinal();
    }
}
