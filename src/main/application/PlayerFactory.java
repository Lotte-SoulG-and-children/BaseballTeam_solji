package main.application;

import main.application.domain.PlayerType;
import main.exception.WrongTypeException;
import main.application.domain.Batter;
import main.application.domain.Pitcher;
import main.application.domain.Player;

public class PlayerFactory {
    public static Player create(String name, int age, double height, int infoOne, int infoTwo,
            PlayerType type) {
        if (type.equals(PlayerType.PITCHER)) {
            return new Pitcher(name, age, height, infoOne, infoTwo);
        }
        if (type.equals(PlayerType.BATTER)) {
            return new Batter(name, age, height, infoOne, infoTwo);
        }
        throw new WrongTypeException();
    }
}
