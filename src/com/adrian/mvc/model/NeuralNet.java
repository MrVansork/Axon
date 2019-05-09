package com.adrian.mvc.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class NeuralNet {

    private static List<NeuralNet> nets = new ArrayList<>();
    public static List<NeuralNet> getNets(){ return nets; }

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty date;
    private final ObjectProperty<User> creator;
    private final StringProperty description;

    public NeuralNet(int id, String name, String description, String date, User creator) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.creator = new SimpleObjectProperty<>(creator);
        nets.add(this);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public User getCreator() {
        return creator.get();
    }

    public ObjectProperty<User> creatorProperty() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator.set(creator);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
