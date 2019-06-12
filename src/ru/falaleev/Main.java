package ru.falaleev;

import ru.falaleev.alphabets.NonTerminalAlphabet;
import ru.falaleev.alphabets.TerminalAlphabet;
import ru.falaleev.grammar.Grammar;
import ru.falaleev.grammar.Rule;
import ru.falaleev.machine.StateMachine;
import ru.falaleev.nonterminal.NonTerminal;
import ru.falaleev.terminals.CharacterTerminal;
import ru.falaleev.terminals.SetTerminal;
import ru.falaleev.terminals.SpecialTerminals;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        TerminalAlphabet terminalAlphabet = grammar.getTerminalAlphabet();
        terminalAlphabet.add(SpecialTerminals.DIGIT);
        terminalAlphabet.add(new CharacterTerminal("MINUS", '-'));
        terminalAlphabet.add(new CharacterTerminal("PLUS", '+'));
        terminalAlphabet.add(new SetTerminal("SEPARATOR", ',', '.'));

        NonTerminalAlphabet nonTerminalAlphabet = grammar.getNonTerminalAlphabet();
        nonTerminalAlphabet.add(new NonTerminal("AtLeastOneDigit"));
        nonTerminalAlphabet.add(new NonTerminal("StartDigits", true));
        nonTerminalAlphabet.add(new NonTerminal("EndDigits", true));

        grammar.addRule(new Rule("S", "MINUS", "AtLeastOneDigit"));
        grammar.addRule(new Rule("S", "PLUS", "AtLeastOneDigit"));
        grammar.addRule(new Rule("AtLeastOneDigit", SpecialTerminals.DIGIT.getName(), "StartDigits"));
        grammar.addRule(new Rule("S", SpecialTerminals.DIGIT.getName(), "StartDigits"));
        grammar.addRule(new Rule("StartDigits", SpecialTerminals.DIGIT.getName(), "StartDigits"));
        grammar.addRule(new Rule("StartDigits", "SEPARATOR", "EndDigits"));
        grammar.addRule(new Rule("EndDigits", SpecialTerminals.DIGIT.getName(), "EndDigits"));

        StateMachine stateMachine = new StateMachine(grammar);

        List<NonTerminal> states = stateMachine.process("+123.45");
        System.out.println("Trace: " + states);
        System.out.println("Result: " + stateMachine.isLastFinal(states));
    }
}
