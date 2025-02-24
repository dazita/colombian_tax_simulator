package model;

import libraries.*;

public class Line {
    
    private String name;
    private SimpleList<Model> models;
    private String fuel;


    public Line(String name, String fuel) {
        this.name = name;
        this.models = new SimpleList<Model>();
        this.fuel = fuel;
    }


    public String getName() {
        return name;
    }

    public SimpleList<Model> getModels() {
        return models;
    }

    public String getFuel() {
        return fuel;
    }

}

