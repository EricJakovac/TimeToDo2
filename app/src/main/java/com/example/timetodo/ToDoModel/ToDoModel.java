package com.example.timetodo.ToDoModel;

import java.time.LocalTime;

public class ToDoModel {
    private int id;
    private int status;
    private String task;
    private String dueTime; // Promijenjeno u String

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueTime() { // Promijenjeno u String
        return dueTime;
    }

    public void setDueTime(String dueTime) { // Promijenjeno u String
        this.dueTime = dueTime;
    }
}