package ru.falaleev.ui.widgets.terminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.*;
import ru.falaleev.ui.forms.terminal.*;


public class TerminalWidget extends BorderPane {
    private TerminalsList list;
    private Terminal terminal;
    private Label text;
    private Button removeBtn;
    private Button editBtn;

    public TerminalWidget(TerminalsList list, Terminal terminal) {
        this.list = list;
        this.terminal = terminal;
        text = new Label();
        text.setAlignment(Pos.BASELINE_LEFT);
        update();

        editBtn = new Button("Изменить");
        editBtn.setAlignment(Pos.BASELINE_RIGHT);
        editBtn.setOnMouseClicked(event -> {
            AbstractTerminalEditDialog<? extends Terminal, ? extends Pane> dialog;
            if(this.terminal instanceof CharacterTerminal) {
                dialog = new CharacterTerminalEditDialog(this.list.getAlphabet(), (CharacterTerminal) this.terminal);
            } else if(this.terminal instanceof RangeTerminal) {
                dialog = new RangeTerminalEditDialog(this.list.getAlphabet(), (RangeTerminal) this.terminal);
            } else if(this.terminal instanceof SetTerminal) {
                dialog = new SetTerminalEditDialog(this.list.getAlphabet(), (SetTerminal) this.terminal);
            } else if(this.terminal instanceof CombinedTerminal) {
                dialog = new CombinedTerminalEditDialog(this.list.getAlphabet(), (CombinedTerminal) this.terminal);
            } else {
                return;
            }
            dialog.showAndWait().ifPresent(created -> {
                TerminalAlphabet alphabet = this.list.getAlphabet();
                alphabet.remove(this.terminal.getName());
                this.terminal = created;
                alphabet.add(created);
            });
            update();
        });

        removeBtn = new Button("Удалить");
        removeBtn.setAlignment(Pos.BASELINE_RIGHT);
        removeBtn.setOnMouseClicked(event -> this.list.remove(this));

        setLeft(text);
        HBox buttons = new HBox(editBtn, removeBtn);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        setRight(buttons);
    }

    public void update() {
        text.setText(terminal.toString());
        setStyle("-fx-border-width: 1px; -fx-border-color: black");
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
