package ru.stqa.pft.mantis.model;

public class Project {
    private int Id;
    private String name;

    public int getId() {
        return Id;
    }

    public Project withId(int id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
