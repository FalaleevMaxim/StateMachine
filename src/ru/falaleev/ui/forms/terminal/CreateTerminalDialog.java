package ru.falaleev.ui.forms.terminal;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.falaleev.core.alphabets.TerminalAlphabet;
import ru.falaleev.core.terminals.*;
import ru.falaleev.ui.util.UiUtil;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterFieldset;
import ru.falaleev.ui.widgets.terminal.fieldset.CharacterSetFieldset;
import ru.falaleev.ui.widgets.terminal.fieldset.RangeFieldset;
import ru.falaleev.ui.widgets.terminal.fieldset.CombinedFieldset;
import ru.falaleev.util.Utils;

import java.util.ArrayList;
import java.util.Set;

public class CreateTerminalDialog extends Dialog<Terminal> {
    private TerminalAlphabet alphabet;
    private Terminal item;
    private TextField nameField;

    private static final String CHARACTER_TAB_NAME = "Символ";
    private static final String RANGE_TAB_NAME = "Диапазон";
    private static final String SET_TAB_NAME = "Набор";
    private static final String COMBINED_TAB_NAME = "Комбинация";

    private TabPane tabPane;
    private Tab characterTab;
    private CharacterFieldset characterFieldset;
    private Tab rangeTab;
    private RangeFieldset rangeFieldset;
    private Tab setTab;
    private CharacterSetFieldset characterSetFieldset;
    private Tab combinedTab;
    private CombinedFieldset combinedFieldset;


    private Button saveBtn;
    private Button cancelBtn;

    public CreateTerminalDialog(TerminalAlphabet alphabet) {
        this.alphabet = alphabet;

        nameField = new TextField();
        HBox nameBox = new HBox(new Label("Название: "), nameField);

        characterTab = new Tab(CHARACTER_TAB_NAME);
        characterTab.setTooltip(new Tooltip("Терминал, который распознаёт один конкретный символ"));
        characterFieldset = new CharacterFieldset();
        characterTab.setContent(characterFieldset);

        rangeTab = new Tab(RANGE_TAB_NAME);
        rangeTab.setTooltip(new Tooltip("Терминал, который распознаёт диапазон символов"));
        rangeFieldset = new RangeFieldset();
        rangeTab.setContent(rangeFieldset);

        setTab = new Tab(SET_TAB_NAME);
        setTab.setTooltip(new Tooltip("Терминал, который распознаёт один из набора символов"));
        characterSetFieldset = new CharacterSetFieldset(null);
        setTab.setContent(characterSetFieldset);

        combinedTab = new Tab(COMBINED_TAB_NAME);
        combinedTab.setTooltip(new Tooltip("Терминал, который объединяет в себе несколько других терминалов и распознаёт символ, если его смог распознать хотя бы один их терминалов."));
        combinedFieldset = new CombinedFieldset(alphabet, null);
        combinedTab.setContent(combinedFieldset);

        tabPane = new TabPane(characterTab, rangeTab, setTab, combinedTab);

        ButtonType cancelType = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().add(cancelType);

        saveBtn = new Button("Сохранить");

        setOnCloseRequest(event -> {
            setResult(item);
        });

        saveBtn.setOnMouseClicked(event -> {
            String name = nameField.getText();
            Tab selected = tabPane.selectionModelProperty().get().getSelectedItem();

            try {
                if(name.isEmpty() && selected==characterTab) {
                    Character c = characterFieldset.getChar();
                    name =String.valueOf(c);
                }
                Utils.checkName(name);
            } catch (RuntimeException e) {
                UiUtil.alert(e);
                return;
            }

            if(alphabet.contains(name)) {
                UiUtil.alert("Алфавит уже содержит нетерминал с таким именем!");
                return;
            }
            try {
                switch (selected.getText()) {
                    case CHARACTER_TAB_NAME:
                        Character c;
                        c = characterFieldset.getChar();
                        item = new CharacterTerminal(name, c);
                        break;
                    case RANGE_TAB_NAME:
                        item = new RangeTerminal(name, rangeFieldset.getFrom(), rangeFieldset.getTo());
                        break;
                    case SET_TAB_NAME:
                        Set<Character> characters = characterSetFieldset.getCharacters();
                        if(characters.isEmpty()) {
                            UiUtil.alert("Нет символов в наборе!");
                            return;
                        }
                        if(characters.size()==1) {
                            UiUtil.alert("Для распознавания одного символа воспользуйтесь вкладкой \"Символ\"");
                            return;
                        }
                        item = new SetTerminal(name, characters);
                        break;
                    case COMBINED_TAB_NAME:
                        Set<Terminal> combined = combinedFieldset.getCombined();
                        if(combined.isEmpty()) {
                            UiUtil.alert("Нет скомбинированных терминалов!");
                            return;
                        }
                        if(combined.size()==1) {
                            UiUtil.alert("Создавать комбинированный терминал из 1 терминала не имеет смысла");
                            return;
                        }
                        item = new CombinedTerminal(name, new ArrayList<>(combined));
                        break;
                    default:
                        break;
                }
            } catch (RuntimeException e) {
                UiUtil.alert(e);
                return;
            }
            setResult(item);
        });

        VBox root = new VBox(nameBox, tabPane, UiUtil.hCenter(saveBtn));
        root.setSpacing(5);
        getDialogPane().setContent(root);
    }
}
