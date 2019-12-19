package ck.train.toDoList.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Singleton (private constructor + getInstance)
public class ToDoData {
    private static ToDoData instance = new ToDoData();
    private static String todoFilename = "todoitems.txt";

    private List<ToDoItem> todoItems;
    private DateTimeFormatter formaatter;

    public static ToDoData getInstance() {
        return instance;
    }

    private ToDoData() {
        formaatter = DateTimeFormatter.ISO_DATE;
    }

    public List<ToDoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<ToDoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public void loadToDoItems () throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(todoFilename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itempPieces = input.split("\t");
                String shortDescription = itempPieces[0];
                String details = itempPieces[1];
                String datestring = itempPieces[2];
                LocalDate deadline = LocalDate.parse(datestring, formaatter);

                ToDoItem item = new ToDoItem(shortDescription, details, deadline);
                todoItems.add(item);
            }
        } finally {
            if(br != null){
                br.close();
            }
        }
    }

    public void storeToDoItems() throws IOException {
        Path path = Paths.get(todoFilename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            for (ToDoItem item : todoItems) {
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formaatter)));
                bw.newLine();
            }
        } finally {
            if(bw != null){
                bw.close();
            }
        }
    }
}
