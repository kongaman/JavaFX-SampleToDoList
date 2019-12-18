package ck.train.toDoList;

import ck.train.toDoList.datamodel.ToDoItem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItem> todoItems;

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

        todoItems = new ArrayList<ToDoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);
    }
}
