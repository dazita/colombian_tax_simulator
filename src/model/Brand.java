package model;

import libraries.*;

public class Brand {
    
    private String name;
    private SimpleList<Line> lines;

    public Brand(String name) {
        this.name = name;
        this.lines = new SimpleList<Line>();
    }

    public String getName() {
        return name;
    }

    public SimpleList<Line> getLines() {
        return lines;
    }
}
