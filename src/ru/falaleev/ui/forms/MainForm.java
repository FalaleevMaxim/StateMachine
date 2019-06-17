package ru.falaleev.ui.forms;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.grammar.Grammar;
import ru.falaleev.core.machine.StateMachine;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.ui.widgets.nonterminal.NonTerminalsList;
import ru.falaleev.ui.widgets.rule.RulesList;
import ru.falaleev.ui.widgets.terminal.TerminalsList;

public class MainForm extends VBox {
    private Grammar grammar = new Grammar();

    SplitPane alphabets;
    SplitPane alphabetsAndRules;
    NonTerminalsList nonTerminalsList;
    TerminalsList terminalsList;
    RulesList rulesList;
    Button createMachineBtn;

    public MainForm() {
        nonTerminalsList = new NonTerminalsList(grammar.getNonTerminalAlphabet());
        HBox.setHgrow(nonTerminalsList, Priority.ALWAYS);

        terminalsList = new TerminalsList(grammar.getTerminalAlphabet());
        HBox.setHgrow(terminalsList, Priority.ALWAYS);

        alphabets = new SplitPane(nonTerminalsList, terminalsList);
        alphabets.setOrientation(Orientation.HORIZONTAL);
        setVgrow(alphabets, Priority.ALWAYS);

        rulesList = new RulesList(grammar);
        VBox.setVgrow(rulesList, Priority.ALWAYS);

        alphabetsAndRules = new SplitPane(alphabets, rulesList);
        alphabetsAndRules.setOrientation(Orientation.VERTICAL);
        setVgrow(alphabetsAndRules, Priority.ALWAYS);

        createMachineBtn = new Button("Создать конечный автомат");
        createMachineBtn.setOnMouseClicked(event -> {
            try {
                new StateMachineDialog(new StateMachine(grammar)).showAndWait();
            } catch (RuntimeException e){
                UiUtil.alert(e);
            }
        });

        HBox createBtnBox = UiUtil.hCenter(createMachineBtn);
        createBtnBox.setPadding(new Insets(5,0,0,5));

        getChildren().addAll(createBtnBox, alphabetsAndRules, rulesList);
    }
}
