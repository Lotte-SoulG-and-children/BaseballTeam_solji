package main.application.domain;

public class Pitcher extends Player {

    private int wins;
    private int lose;
    private double era;

    public Pitcher() {
    }

    public Pitcher(String name, int age, double height, int wins, int lose) {
        super(name, age, height);
        this.wins = wins;
        this.lose = lose;
        this.era = (double) wins / (wins + lose);
    }

    public Pitcher(int id, String name, int age, double height, int wins, int lose) {
        super(id, name, age, height);
        this.wins = wins;
        this.lose = lose;
        this.era = (double) wins / (wins + lose);
    }

    public int getWins() {
        return wins;
    }

    public int getLose() {
        return lose;
    }

    public double getEra() {
        return era;
    }

    @Override
    public String getSerializedValue() {
        return "투수, " + super.getSerializedValue() + ", " + wins + ", " + lose;
    }
}
