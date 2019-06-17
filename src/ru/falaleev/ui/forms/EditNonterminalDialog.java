package ru.falaleev.ui.forms;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.NonTerminalAlphabet;
import ru.falaleev.core.nonterminal.NonTerminal;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.util.Utils;

public class EditNonterminalDialog extends Dialog<NonTerminal> {
    private NonTerminalAlphabet alphabet;
    private NonTerminal item;
    private boolean created;

    private TextField nameField;
    private CheckBox isFinal;
    private Button saveBtn;
    private Button cancelBtn;

    public EditNonterminalDialog(NonTerminalAlphabet alphabet, NonTerminal nonTerminal) {
        this.alphabet = alphabet;
        this.item = nonTerminal;

        nameField = new TextField();
        isFinal = new CheckBox("Финальный");
        HBox content = new HBox();
        content.getChildren().addAll(new Label("Имя: "), nameField, isFinal);

        if(item!=null) {
            nameField.setText(item.getName());
            isFinal.setSelected(item.isFinal());
            if( NonTerminal.BASIC_ALPHABET.contains(item) ) {
                nameField.setDisable(true);
            }

            if (NonTerminal.FAIL.getName().equals(item.getName())) {
                isFinal.setDisable(true);
                isFinal.setTooltip(new Tooltip("Стандартный нетерминал Fail не может быть финальным"));
            } else if (NonTerminal.FINAL.getName().equals(item.getName())) {
                isFinal.setDisable(true);
                isFinal.setTooltip(new Tooltip("Стандартный нетерминал Final не может быть нефинальным"));
            }
        }

        ButtonType cancelType = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().add(cancelType);

        saveBtn = new Button("Сохранить");

        setOnCloseRequest(event -> {
            if(created) setResult(item);
            else setResult(null);
        });

        saveBtn.setOnMouseClicked(event -> {
            String name = nameField.getText();
            try {
                Utils.checkName(name);
            } catch (RuntimeException e) {
                UiUtil.alert(e);
                return;
            }

            if((item==null || !item.getName().equals(name)) && alphabet.contains(name)) {
                UiUtil.alert("Алфавит уже содержит нетерминал с таким именем!");
                return;
            }
            if(item==null || !item.getName().equals(name)) {
                item = new NonTerminal(name, isFinal.isSelected());
                created = true;
            } else {
                item.setFinal(isFinal.isSelected());
            }

            close();
        });

        VBox root = new VBox();
        root.getChildren().addAll(content, UiUtil.hCenter(saveBtn));
        getDialogPane().setContent(root);
    }
}
