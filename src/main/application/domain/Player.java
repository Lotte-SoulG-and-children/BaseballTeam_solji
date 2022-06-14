package main.application.domain;

import java.util.Objects;

public abstract class Player {

    private int id;
    private String name;
    private int age;
    private double height;

    public Player() {
    }

    public Player(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Player(int id, String name, int age, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getSerializedValue() {
        return id + ", " + name + ", " + age + ", " + height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
