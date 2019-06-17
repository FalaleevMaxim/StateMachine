package ru.falaleev;

import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.grammar.Grammar;
import ru.falaleev.core.grammar.Rule;
import ru.falaleev.core.machine.StateMachine;
import ru.falaleev.core.machine.StateMachineMove;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.core.terminals.CharacterTerminal;
import ru.falaleev.core.terminals.SetTerminal;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.core.terminals.TerminalTemplates;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        TerminalAlphabet terminalAlphabet = grammar.getTerminalAlphabet();
        Terminal digit = TerminalTemplates.DIGIT.get();
        terminalAlphabet.add(digit);
        terminalAlphabet.add(new CharacterTerminal("MINUS", '-'));
        terminalAlphabet.add(new CharacterTerminal("PLUS", '+'));
        terminalAlphabet.add(new SetTerminal("SEPARATOR", ',', '.'));

        NonTerminalAlphabet nonTerminalAlphabet = grammar.getNonTerminalAlphabet();
        nonTerminalAlphabet.add(new NonTerminal("AtLeastOneDigit"));
        nonTerminalAlphabet.add(new NonTerminal("StartDigits", true));
        nonTerminalAlphabet.add(new NonTerminal("EndDigits", true));

        grammar.addRule(new Rule("S", "MINUS", "AtLeastOneDigit"));
        grammar.addRule(new Rule("S", "PLUS", "AtLeastOneDigit"));
        grammar.addRule(new Rule("AtLeastOneDigit", digit.getName(), "StartDigits"));
        grammar.addRule(new Rule("S", digit.getName(), "StartDigits"));
        grammar.addRule(new Rule("StartDigits", digit.getName(), "StartDigits"));
        grammar.addRule(new Rule("StartDigits", "SEPARATOR", "EndDigits"));
        grammar.addRule(new Rule("EndDigits", digit.getName(), "EndDigits"));

        StateMachine stateMachine = new StateMachine(grammar);

        List<StateMachineMove> states = stateMachine.process("+123.45");
        System.out.println("Trace:\n" + states.toString()
                .replaceAll(",", "\n")
                .replaceFirst("\\[", "")
                .replaceFirst("]", ""));
        System.out.println("Result: " + stateMachine.isLastFinal(states));
    }
}
