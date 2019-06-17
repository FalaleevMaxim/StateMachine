package ru.falaleev.ui.widgets.rule;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.falaleev.core.grammar.Rule;
import ru.falaleev.ui.forms.EditRuleDialog;

import java.util.Optional;


public class RuleWidget extends BorderPane {
    private RulesList list;
    private Rule rule;
    private TextField left;
    private TextField terminal;
    private TextField right;
    private Button removeBtn;
    private Button editBtn;

    public RuleWidget(RulesList list, Rule rule) {
        this.list = list;
        this.rule = rule;

        left = new TextField();
        left.setDisable(true);
        terminal = new TextField();
        terminal.setDisable(true);
        right = new TextField();
        right.setDisable(true);

        editBtn = new Button("Изменить");
        editBtn.setAlignment(Pos.BASELINE_RIGHT);
        editBtn.setOnMouseClicked(event -> {
            Optional<Rule> edited = new EditRuleDialog(this.list.getGrammar(), this.rule).showAndWait();
            if (edited.isPresent()) {
                this.list.getGrammar().removeRule(this.rule);
                rule.setLeft(edited.get().getLeft());
                rule.setTerminal(edited.get().getTerminal());
                this.list.getGrammar().addRule(this.rule);
            }
            update();
        });

        removeBtn = new Button("Удалить");
        removeBtn.setAlignment(Pos.BASELINE_RIGHT);
        removeBtn.setOnMouseClicked(event -> this.list.remove(this));

        HBox ruleContent = new HBox(left, new Label("->"), terminal, right);
        ruleContent.setAlignment(Pos.BASELINE_LEFT);
        ruleContent.setSpacing(3);
        setLeft(ruleContent);
        HBox buttons = new HBox(editBtn, removeBtn);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        setRight(buttons);

        update();
    }

    public void update() {
        left.setText(rule.getLeft());
        terminal.setText(rule.getTerminal());
        right.setText(rule.getRight());
        setStyle("-fx-border-width: 1px; -fx-border-color: black");
    }

    public Rule getRule() {
        return rule;
    }
}
