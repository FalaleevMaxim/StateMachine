package ru.falaleev.ui.widgets.nonterminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.ui.forms.EditNonterminalDialog;
import ru.falaleev.ui.util.UiUtil;

public class NonTerminalsList extends VBox {
    private NonTerminalAlphabet alphabet;

    private VBox list;
    private ScrollPane scroll;
    private Label label;
    private Button addBtn;

    public NonTerminalsList(NonTerminalAlphabet alphabet) {
        this.alphabet = alphabet;
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
                new EditNonterminalDialog(this.alphabet, null)
                        .showAndWait()
                        .ifPresent(this::addNonTerminal));

        getChildren().addAll(UiUtil.hCenter(label), scroll, UiUtil.hCenter(addBtn));

        this.alphabet.getNonterminals().values()
                .forEach(nonTerminal ->
                        list.getChildren().add(new NonTerminalWidget(this, nonTerminal)));
    }

    public void addNonTerminal(NonTerminal nonTerminal) {
        alphabet.add(nonTerminal);
        list.getChildren().add(new NonTerminalWidget(this, nonTerminal));
    }

    public void remove(NonTerminalWidget nonTerminalWidget) {
        NonTerminal item = nonTerminalWidget.getNonTerminal();
        if (NonTerminal.BASIC_ALPHABET.contains(item)) {
            UiUtil.alert("Нельзя удалять базовые нетерминалы");
            return;
        }
        alphabet.remove(item.getName());
        list.getChildren().remove(nonTerminalWidget);
    }

    public NonTerminalAlphabet getAlphabet() {
        return alphabet;
    }
}
