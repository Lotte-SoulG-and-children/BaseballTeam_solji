package main.application.domain;

public class Batter extends Player {

    private int batCounts;
    private int hits;
    private double hitAvg;

    public Batter() {
    }

    public Batter(String name, int age, double height, int batCounts, int hits) {
        super(name, age, height);
        this.batCounts = batCounts;
        this.hits = hits;
        this.hitAvg = (double) hits / batCounts;
    }

    public Batter(int id, String name, int age, double height, int batCounts, int hits) {
        super(id, name, age, height);
        this.batCounts = batCounts;
        this.hits = hits;
        this.hitAvg = (double) hits / batCounts;
    }

    @Override
    public String getSerializedValue() {
        return "타자, " + super.getSerializedValue() + ", " + batCounts + ", " + hits;
    }

    public int getBatCounts() {
        return batCounts;
    }

    public int getHits() {
        return hits;
    }

    public double getHitAvg() {
        return hitAvg;
    }
}
