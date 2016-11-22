package com.example.android.todolist.todo;

/**
 * Created by Abshafi on 11/21/2016.
 */
public class Todo {

    String todoStatus;
    String dateAndtime;

    public void setDateAndtime(String dateAndtime) {
        this.dateAndtime = dateAndtime;
    }

    public String getDateAndtime() {
        return dateAndtime;
    }


    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    @Override
    public String toString() {
        return todoStatus;
    }
}
