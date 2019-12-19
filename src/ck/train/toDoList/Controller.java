package ck.train.toDoList;

import ck.train.toDoList.datamodel.ToDoData;
import ck.train.toDoList.datamodel.ToDoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Controller {

    private List<ToDoItem> todoItems;

    @FXML
    private ListView<ToDoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem oldValue, ToDoItem newValue) {
                if(newValue != null) {
                    ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    deadlineLabel.setText(item.getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                            .withLocale(Locale.GERMAN)));
                }
            }
        });

        todoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("toDoItemDialog.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            System.out.println("Couldn't load dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK pressed");
        } else {
            System.out.println("Canceled");
        }
    }

    @FXML
    public void handleClickListView() {
        ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
        System.out.println("The selected item is: " + item);
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.GERMAN)));
    }
}
