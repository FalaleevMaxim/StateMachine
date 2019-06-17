package ru.falaleev.ui.widgets.terminal.fieldset;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.CombinedTerminal;
import ru.falaleev.core.terminals.SetTerminal;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.ui.forms.SelectDialog;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.ui.widgets.terminal.CombinedTerminalItemWidget;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinedFieldset extends VBox {
    private final TerminalAlphabet alphabet;
    private Set<Terminal> combined = new HashSet<>();

    private VBox list;
    private ScrollPane scroll;
    private Label label;
    private Button addBtn;

    public CombinedFieldset(TerminalAlphabet alphabet, CombinedTerminal item) {
        this.alphabet = alphabet;

        setMinHeight(200);
        label = new Label("Скомбинированы:");

        list = new VBox();
        VBox.setVgrow(list, Priority.ALWAYS);
        scroll = new ScrollPane(list);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        setVgrow(scroll, Priority.ALWAYS);
        setStyle("-fx-border-width: 1px; -fx-border-color: grey");

        addBtn = new Button("Добавить");
        addBtn.setOnMouseClicked(event ->
        {
            List<Terminal> terminals = this.alphabet.getTerminals().values().stream().filter(terminal -> !terminal.equals(item)).collect(Collectors.toList());
            new SelectDialog<>(terminals)
                    .showAndWait()
                    .ifPresent(this::addTerminal);
        });

        getChildren().addAll(UiUtil.hCenter(label), scroll, UiUtil.hCenter(addBtn));

        if (item != null) {
            item.getCombinedTerminals().forEach(this::addTerminal);
        }
    }

    public void addTerminal(Terminal terminal) {
        if (combined.add(terminal)) {
            list.getChildren().add(new CombinedTerminalItemWidget(this, terminal));
        }
    }

    public void remove(CombinedTerminalItemWidget terminalWidget) {
        Terminal item = terminalWidget.getTerminal();
        combined.remove(item);
        list.getChildren().remove(terminalWidget);
    }

    public Set<Terminal> getCombined() {
        return combined;
    }
}
