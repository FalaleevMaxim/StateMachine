package ru.falaleev.ui.widgets.rule;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.grammar.Grammar;
import ru.falaleev.core.grammar.Rule;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.ui.forms.EditRuleDialog;
import ru.falaleev.ui.util.UiUtil;

import java.util.HashSet;
import java.util.Set;

public class RulesList extends VBox {
    private Grammar grammar;

    private VBox list;
    private ScrollPane scroll;
    private Label label;
    private Button addBtn;

    public RulesList(Grammar grammar) {
        this.grammar = grammar;
        label = new Label("Нетерминалы");

        list = new VBox();
        scroll = new ScrollPane(list);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        setVgrow(scroll, Priority.ALWAYS);
        setStyle("-fx-border-width: 1px; -fx-border-color: grey");

        addBtn = new Button("Добавить");
        addBtn.setAlignment(Pos.BASELINE_CENTER);
        addBtn.setOnMouseClicked(event ->
                new EditRuleDialog(this.grammar, null)
                    .showAndWait()
                    .ifPresent(this::addRule));


        getChildren().addAll(UiUtil.hCenter(label), scroll, UiUtil.hCenter(addBtn));

        grammar.getRules().forEach(nonTerminal ->
                        list.getChildren().add(new RuleWidget(this, nonTerminal)));
    }

    public void addRule(Rule rule) {
        try {
            grammar.addRule(rule);
            list.getChildren().add(new RuleWidget(this, rule));
        } catch (RuntimeException e) {
            UiUtil.alert(e);
        }
    }

    public void remove(RuleWidget ruleWidget) {
        Rule item = ruleWidget.getRule();
        grammar.removeRule(item);
        list.getChildren().remove(ruleWidget);
    }

    public Set<Rule> getRules() {
        return grammar.getRules();
    }

    public TerminalAlphabet getTerminalAlphabet() {
        return grammar.getTerminalAlphabet();
    }

    public NonTerminalAlphabet getNonTerminalAlphabet() {
        return grammar.getNonTerminalAlphabet();
    }

    public Grammar getGrammar() {
        return grammar;
    }
}
