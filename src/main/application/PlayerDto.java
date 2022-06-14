package main.application;

import java.util.List;
import main.application.domain.PlayerType;

public class PlayerDto {

    private Integer id;
    private String name;
    private int age;
    private double height;
    private int infoOne;
    private int infoTwo;
    private PlayerType type;

    public static PlayerDto create(List<String> info, PlayerType type) {
        return new PlayerDto(null, info.get(0), Integer.parseInt(info.get(1)),
                Double.parseDouble(info.get(2)), Integer.parseInt(info.get(3)),
                Integer.parseInt(info.get(4)), type);
    }

    public static PlayerDto create(int id, List<String> info, PlayerType type) {
        return new PlayerDto(id, info.get(0), Integer.parseInt(info.get(1)),
                Double.parseDouble(info.get(2)), Integer.parseInt(info.get(3)),
                Integer.parseInt(info.get(4)), type);
    }

    private PlayerDto() {
    }

    private PlayerDto(Integer id, String name, int age, double height, int infoOne, int infoTwo,
            PlayerType type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.infoOne = infoOne;
        this.infoTwo = infoTwo;
        this.type = type;
    }

    public Integer getId() {
        return id;
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

    public int getInfoOne() {
        return infoOne;
    }

    public int getInfoTwo() {
        return infoTwo;
    }

    public PlayerType getType() {
        return type;
    }
}
