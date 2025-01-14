package de.lubowiecki.gui;

public class Todo {

    private String title;

    private boolean done;

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        done = true;
    }

    public void toggleDone() {
        done = !done;
    }

    @Override
    public String toString() {
        return title + ", " + done;
    }
}
