package ru.falaleev.ui.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ru.falaleev.core.grammar.Grammar;
import ru.falaleev.core.grammar.Rule;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.ui.util.UiUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class EditRuleDialog extends Dialog<Rule> {
    private final Grammar grammar;
    private Rule item;
    private Button selectLeftBtn = new Button("Выбрать нетерминал");
    private Button selectTerminalBtn = new Button("Выбрать терминал");
    private Button selectRightBtn = new Button("Выбрать нетерминал");

    private Label leftSelected = new Label();
    private Label terminalSelected = new Label();
    private Label rightSelected = new Label(NonTerminal.FINAL.getName());


    public EditRuleDialog(Grammar grammar, Rule item) {
        this.grammar = grammar;
        this.item = item;

        GridPane grid = new GridPane();
        grid.addColumn(0, pane(selectLeftBtn), pane(leftSelected));
        grid.addColumn(1, pane(selectTerminalBtn), pane(terminalSelected));
        grid.addColumn(2, pane(new Label("->")), pane(new Label("->")));
        grid.addColumn(3, pane(selectRightBtn), pane(rightSelected));

        selectLeftBtn.setOnMouseClicked(event ->
                new SelectDialog<>(new ArrayList<>(this.grammar.getNonTerminalAlphabet().getNonterminals().values()))
                        .showAndWait()
                        .ifPresent(s -> leftSelected.setText(s.getName())));

        selectTerminalBtn.setOnMouseClicked(event ->
                new SelectDialog<>(new ArrayList<>(this.grammar.getTerminalAlphabet().getTerminals().values()))
                        .showAndWait()
                        .ifPresent(s -> terminalSelected.setText(s.getName())));

        selectRightBtn.setOnMouseClicked(event ->
                new SelectDialog<>(new ArrayList<>(this.grammar.getNonTerminalAlphabet().getNonterminals().values()))
                        .showAndWait()
                        .ifPresent(nonTerminal -> rightSelected.setText(nonTerminal.getName())));

        if(item!=null) {
            leftSelected.setText(item.getLeft());
            terminalSelected.setText(item.getTerminal());
            rightSelected.setText(item.getRight());
        }

        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        Button close = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        close.setVisible(false);

        close = new Button("Закрыть");
        close.setOnMouseClicked(event -> {
            setResult(null);
            close();
        });

        setOnCloseRequest(event -> setResult(this.item));

        Button save = new Button("Сохранить");
        save.setOnMouseClicked(event -> {
            Rule existingRule = this.grammar.getRule(leftSelected.getText(), terminalSelected.getText());
            if (existingRule != null && existingRule != this.item) {
                UiUtil.alert("Правило с такими же левой частью и терминалом уже есть в грамматике!");
                this.item = null;
            } else if(this.item==null || !this.item.getLeft().equals(leftSelected.getText()) || !this.item.getTerminal().equals(terminalSelected.getText())) {
                this.item = new Rule(leftSelected.getText(), terminalSelected.getText(), rightSelected.getText());
            } else {
                this.item.setRight(rightSelected.getText());
            }
            setResult(this.item);
        });

        HBox buttons = new HBox(close, save);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        buttons.setSpacing(5);
        VBox content = new VBox(grid, buttons);
        content.setSpacing(5);
        getDialogPane().setContent(content);
    }

    private Pane pane(Node node) {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-border-color: grey; -fx-border-width: 1px");
        pane.setPadding(new Insets(2, 2, 2, 2));
        pane.setCenter(node);
        return pane;
    }
}
