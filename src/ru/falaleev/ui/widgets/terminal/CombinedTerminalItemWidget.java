package ru.falaleev.ui.widgets.terminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.ui.widgets.terminal.fieldset.CombinedFieldset;


public class CombinedTerminalItemWidget extends BorderPane {
    private CombinedFieldset list;
    private Terminal terminal;
    private Label text;
    private Button removeBtn;

    public CombinedTerminalItemWidget(CombinedFieldset list, Terminal terminal) {
        this.list = list;
        this.terminal = terminal;
        text = new Label();
        text.setAlignment(Pos.BASELINE_LEFT);
        update();

        removeBtn = new Button("Удалить");
        removeBtn.setAlignment(Pos.BASELINE_RIGHT);
        removeBtn.setOnMouseClicked(event -> this.list.remove(this));

        setLeft(text);
        HBox buttons = new HBox(removeBtn);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        setRight(buttons);
        //HBox.setHgrow(this, Priority.ALWAYS);
    }

    public void update() {
        text.setText(terminal.toString());
        setStyle("-fx-border-width: 1px; -fx-border-color: black");
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
