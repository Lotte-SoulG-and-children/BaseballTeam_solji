package main.application;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import main.exception.InValidDataException;
import main.repository.BaseballDao;
import main.application.domain.Player;

public class PlayerService {

    private final BaseballDao baseballDao;

    public PlayerService() {
        this.baseballDao = new BaseballDao();
    }

    public void create(PlayerDto playerDto) {
        final Player player = PlayerFactory.create(playerDto.getName(), playerDto.getAge(),
                playerDto.getHeight(),
                playerDto.getInfoOne(), playerDto.getInfoTwo(), playerDto.getType());
        baseballDao.insert(player);
    }

    public void update(PlayerDto playerDto) {
        final Player player = PlayerFactory.create(playerDto.getName(), playerDto.getAge(),
                playerDto.getHeight(),
                playerDto.getInfoOne(), playerDto.getInfoTwo(), playerDto.getType());
        baseballDao.update(player, playerDto.getId());
    }

    public void delete(int id) {
        final Optional<Player> player = baseballDao.select(id);
        if (player.isEmpty()) {
            throw new InValidDataException();
        }
        baseballDao.delete(id);
    }

    public List<Player> findAll() {
        final List<Player> players = baseballDao.select();
        if (players.isEmpty()) {
            System.out.println("현재 저장된 정보가 없습니다.");
            return Collections.emptyList();
        }
        return players;
    }

    public Player findBy(int id) {
        final Optional<Player> player = baseballDao.select(id);
        if (player.isEmpty()) {
            throw new InValidDataException();
        }
        return player.get();
    }

    public List<Player> findBy(String name) {
        final List<Player> players = baseballDao.select(name);
        if (players.isEmpty()) {
            throw new InValidDataException();
        }
        return players;
    }

    public void save() throws IOException {
        baseballDao.save();
    }

    public void load() throws IOException {
        baseballDao.load();
    }
}
