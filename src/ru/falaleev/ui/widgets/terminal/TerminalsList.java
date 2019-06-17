package ru.falaleev.ui.widgets.terminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.Terminal;
import ru.falaleev.core.terminals.TerminalTemplates;
import ru.falaleev.ui.forms.terminal.CreateTerminalDialog;
import ru.falaleev.ui.util.UiUtil;

public class TerminalsList extends VBox {
    private TerminalAlphabet alphabet;

    private VBox list;
    private ScrollPane scroll;
    private Label label;
    private Button addBtn;

    public TerminalsList(TerminalAlphabet alphabet) {
        this.alphabet = alphabet;
        label = new Label("Tерминалы");

        list = new VBox();
        scroll = new ScrollPane(list);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        setVgrow(scroll, Priority.ALWAYS);
        setStyle("-fx-border-width: 1px; -fx-border-color: grey");

        addBtn = new Button("Добавить");
        addBtn.setAlignment(Pos.BASELINE_CENTER);
        addBtn.setOnMouseClicked(event ->
                new CreateTerminalDialog(this.alphabet)
                    .showAndWait()
                    .ifPresent(this::addTerminal));

        getChildren().addAll(UiUtil.hCenter(label), scroll, UiUtil.hCenter(addBtn));

        this.alphabet.add(TerminalTemplates.DIGIT.get());
        this.alphabet.add(TerminalTemplates.WHITESPACE.get());
        this.alphabet.add(TerminalTemplates.WHITESPACES.get());
        this.alphabet.add(TerminalTemplates.NEWLINE.get());
        this.alphabet.add(TerminalTemplates.RETURN.get());
        this.alphabet.add(TerminalTemplates.NEWLINES.get());
        this.alphabet.add(TerminalTemplates.UNDERLINE.get());
        this.alphabet.add(TerminalTemplates.LATIN_LOWER.get());
        this.alphabet.add(TerminalTemplates.LATIN_UPPER.get());
        this.alphabet.add(TerminalTemplates.LATIN.get());
        this.alphabet.add(TerminalTemplates.CYRILLIC_LOWER.get());
        this.alphabet.add(TerminalTemplates.CYRILLIC_UPPER.get());
        this.alphabet.add(TerminalTemplates.CYRILLIC.get());
        this.alphabet.add(TerminalTemplates.LOWER.get());
        this.alphabet.add(TerminalTemplates.UPPER.get());

        this.alphabet.getTerminals().values()
                .forEach(terminal ->
                        list.getChildren().add(new TerminalWidget(this, terminal)));
    }

    public void addTerminal(Terminal terminal) {
        alphabet.add(terminal);
        list.getChildren().add(new TerminalWidget(this, terminal));
    }

    public void remove(TerminalWidget terminalWidget) {
        Terminal item = terminalWidget.getTerminal();
        alphabet.remove(item.getName());
        list.getChildren().remove(terminalWidget);
    }

    public TerminalAlphabet getAlphabet() {
        return alphabet;
    }
}
