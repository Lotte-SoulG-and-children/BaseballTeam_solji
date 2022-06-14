package main.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import main.exception.InValidDataException;
import main.exception.WrongFileException;
import main.application.domain.Batter;
import main.application.domain.Pitcher;
import main.application.domain.Player;

public class BaseballDao {

    private static final String DELIMITER = ", ";
    private static final File FILE = new File(
            "/Users/soulg/Desktop/코딩가딩가딩/lotte/work7/baseball.txt");
    private static int SEQUENCE = 1;
    private List<Player> players = new ArrayList<>();

    public void insert(Player player) {
        player.setId(SEQUENCE++);
        players.add(player);
    }

    public void update(Player player, int id) {
        player.setId(id);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(player)) {
                players.set(i, player);
                return;
            }
        }
    }

    public void delete(int id) {
        players.remove(id);
    }

    public List<Player> select() {
        return players;
    }

    public Optional<Player> select(int id) {
        return Optional.of(players.get(id));
    }

    public List<Player> select(String name) {
        return players.stream()
                .filter(player -> name.equals(player.getName()))
                .collect(Collectors.toList());
    }

    public void load() throws IOException {
        List<List<String>> data = new ArrayList<>();

        final BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE));
        String str = bufferedReader.readLine();
        while (str != null) {
            data.add(Arrays.asList(str.split(DELIMITER)));
            str = bufferedReader.readLine();
        }
        bufferedReader.close();

        try {
            players = parseToPlayer(data);
//            loadPlayers.sort((p1, p2) -> {
//                return p1.getId() - p2.getId();
//            });
            players.sort(Comparator.comparingInt(Player::getId));
            SEQUENCE = players.get(players.size() - 1).getId() + 1;
        } catch (Exception e) {
            throw new WrongFileException();
        }
    }

    private static List<Player> parseToPlayer(List<List<String>> data) {
        List<Player> players = new ArrayList<>();
        for (List<String> datum : data) {
            final String position = datum.get(0);
            datum = datum.subList(1, 7);
            if (Objects.equals(position, "투수")) {
                players.add(new Pitcher(
                        Integer.parseInt(datum.get(0)),
                        datum.get(1),
                        Integer.parseInt(datum.get(2)),
                        Double.parseDouble(datum.get(3)),
                        Integer.parseInt(datum.get(4)),
                        Integer.parseInt(datum.get(5))
                ));
                continue;
            }
            if (Objects.equals(position, "타자")) {
                players.add(new Batter(
                        Integer.parseInt(datum.get(0)),
                        datum.get(1),
                        Integer.parseInt(datum.get(2)),
                        Double.parseDouble(datum.get(3)),
                        Integer.parseInt(datum.get(4)),
                        Integer.parseInt(datum.get(5))
                ));
                continue;
            }
            throw new InValidDataException();
        }
        return players;
    }

    public void save() throws IOException {
        final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
        for (Player player : players) {
            printWriter.println(player.getSerializedValue());
        }
        printWriter.close();
    }
}
