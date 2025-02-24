package model;

public class Model {

    private Integer year;
    private Integer prize;

    public Model(Integer year, Integer prize) {
        this.year = year;
        this.prize = prize;
    }

    public Integer getPrize() {
        return prize;
    }

    public Integer getYear() {
        return year;
    }
}
