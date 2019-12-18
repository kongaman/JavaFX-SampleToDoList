package ck.train.toDoList;

import ck.train.toDoList.datamodel.ToDoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller {

    private List<ToDoItem> todoItems;

    @FXML
    private ListView<ToDoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;

    public void initialize() {
        ToDoItem item1 = new ToDoItem("Mail birthday card", "Buy a 30th birthday card for John",
                LocalDate.of(2020, Month.JULY,23));
        ToDoItem item2 = new ToDoItem("Dr's Appointment", "See Dr. Smith, bring paperwork",
                LocalDate.of(2020, Month.JANUARY,21));
        ToDoItem item3 = new ToDoItem("Finish desgin proposal for client", "i promised i'd mail the website mockups by Feb 22",
                LocalDate.of(2020, Month.FEBRUARY,22));
        ToDoItem item4 = new ToDoItem("Pickup Doug on the train station", "Doug arrives on dec 12th on the 5:00 train",
                LocalDate.of(2019, Month.DECEMBER,12));
        ToDoItem item5 = new ToDoItem("Pickup dry cleaning", "Clothes should be ready by Friday",
                LocalDate.of(2019, Month.DECEMBER,20));
        ToDoItem item6 = new ToDoItem("Schnuggi berschde", "Von vorne, von hinten...",
                LocalDate.of(2019, Month.DECEMBER,19));

        todoItems = new ArrayList<ToDoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);
        todoItems.add(item6);

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

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
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
