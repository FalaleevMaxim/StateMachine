package ru.falaleev.ui.widgets.nonterminal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.ui.forms.EditNonterminalDialog;
import ru.falaleev.ui.util.UiUtil;

import java.util.Optional;


public class NonTerminalWidget extends BorderPane {
    private NonTerminalsList list;
    private NonTerminal item;
    private Label text;
    private Button removeBtn;
    private Button editBtn;

    public NonTerminalWidget(NonTerminalsList list, NonTerminal item) {
        this.list = list;
        this.item = item;
        text = new Label();
        text.setAlignment(Pos.BASELINE_LEFT);
        update();

        editBtn = new Button("Изменить");
        editBtn.setAlignment(Pos.BASELINE_RIGHT);
        editBtn.setOnMouseClicked(event -> {
            NonTerminalAlphabet alphabet = this.list.getAlphabet();
            Optional<NonTerminal> edited = new EditNonterminalDialog(alphabet, this.item).showAndWait();
            try {
                if (edited.isPresent()) {
                    //Нетерминал удаляется из алвавита, изменяется и добавляется обратно из-за того что в алфавите он хранится в HashSet,
                    // а поле имя участвует в equals и hashcode. И при изменении имени нетерминал может не находиться в алфавите.
                    alphabet.remove(this.item.getName());
                    this.item.setName(edited.get().getName());
                    this.item.setFinal(edited.get().isFinal());
                    alphabet.add(this.item);
                }
                update();
            } catch (RuntimeException e) {
                UiUtil.alert(e);
            }
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
        text.setText(item.toString());
        setStyle("-fx-border-width: 1px; -fx-border-color: black");
    }

    public NonTerminal getNonTerminal() {
        return item;
    }
}
