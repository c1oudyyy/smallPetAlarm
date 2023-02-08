package com.example.smallpetalarm;

public class Note {
    int id;
    String todo;
    boolean isSelected;

    public Note(int id, String todo) {
        this.id = id;
        this.todo = todo;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
