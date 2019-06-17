package ru.falaleev.ui.forms;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.falaleev.core.machine.StateMachine;
import ru.falaleev.core.machine.StateMachineMove;
import ru.falaleev.ui.util.UiUtil;

import java.util.List;

public class StateMachineDialog extends Dialog<String> {
    private final StateMachine stateMachine;

    private TextArea input;
    private Button recognizeBtn;
    private ListView<StateMachineMove> movesOutput;
    private Label resultOutput;

    public StateMachineDialog(StateMachine stateMachine) {
        this.stateMachine = stateMachine;

        input = new TextArea();
        input.setPromptText("Введите цепочку, которую нужно распознать");
        VBox.setVgrow(input, Priority.ALWAYS);

        movesOutput = new ListView<>();
        ScrollPane listScroll = new ScrollPane(movesOutput);
        listScroll.setFitToWidth(true);
        listScroll.setFitToHeight(true);
        VBox.setVgrow(listScroll, Priority.SOMETIMES);

        resultOutput = new Label("Введите текст и нажмите \"Распознать\"");

        recognizeBtn = new Button("Распознать");

        recognizeBtn.setOnMouseClicked(event -> {
            List<StateMachineMove> moves = this.stateMachine.process(input.getText());
            boolean result = this.stateMachine.isLastFinal(moves);

            movesOutput.getItems().clear();
            movesOutput.getItems().addAll(moves);
            resultOutput.setText("Цепочка " + (result ? "" : "не ") + "принадлежит языку");
        });

        VBox content = new VBox(input, UiUtil.hCenter(recognizeBtn), resultOutput, listScroll);
        content.setSpacing(5);
        getDialogPane().setContent(content);

        getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
        Button closeBtn = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        closeBtn.setText("Закрыть");
    }
}
