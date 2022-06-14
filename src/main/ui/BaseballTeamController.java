package main.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import main.exception.InValidDataException;
import main.application.PlayerDto;
import main.application.PlayerService;
import main.application.domain.PlayerType;
import main.application.domain.Batter;
import main.application.domain.Pitcher;
import main.application.domain.Player;

public class BaseballTeamController {

    private final PlayerService playerService;
    private final Scanner scanner;

    public BaseballTeamController() {
        this.playerService = new PlayerService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("[ 야구 팀 관리 ]");
        System.out.println(" >> 파일을 불러옵니다.");

        try {
            playerService.load();
            System.out.println(" >> 파일을 정상적으로 불러왔습니다.");
        } catch (IOException e) {
            System.out.println(" >> 파일 불러오기에 실패했습니다.");
        }

        while (true) {
            boolean flag = false;

            System.out.println("[ 야구 팀 관리 ]");
            System.out.println("1. 전체보기");
            System.out.println("2. 이름으로 조회하기");
            System.out.println("3. 번호로 조회하기");
            System.out.println("4. 추가");
            System.out.println("5. 수정");
            System.out.println("6. 삭제");
            System.out.println("7. 파일에 저장");
            System.out.println("8. 종료");

            final int sel = Integer.parseInt(scanner.next());
            switch (sel) {
                case 1:
                    final List<Player> allPlayer = playerService.findAll();
                    for (Player player : allPlayer) {
                        printPlayerInfo(player);
                    }
                    break;
                case 2:
                    System.out.println("검색하려는 선수의 이름을 입력해 주세요.");
                    final String name = scanner.next();
                    final List<Player> playersByName = playerService.findBy(name);
                    for (Player player : playersByName) {
                        printPlayerInfo(player);
                    }
                    break;
                case 3:
                    try {
                        System.out.println("검색하려는 선수의 번호 입력해 주세요.");
                        final int id = scanner.nextInt();
                        final Player playerById = playerService.findBy(id - 1);
                        printPlayerInfo(playerById);
                    } catch (InValidDataException e) {
                        System.out.println("해당하는 번호의 선수가 없습니다.");
                    }
                    break;
                case 4:
                    List<String> input = getPlayerDefaultInfo();

                    System.out.println("선수 포지션을 입력해 주세요. (1)투수/(2)타자");
                    String position = scanner.next();
                    if ("1".equals(position)) {
                        final List<String> totalInfo = getPitcherInfo(input);
                        final PlayerDto playerDto = PlayerDto.create(totalInfo, PlayerType.PITCHER);
                        playerService.create(playerDto);
                    }
                    if ("2".equals(position)) {
                        final List<String> totalInfo = getBatterInfo(input);
                        final PlayerDto playerDto = PlayerDto.create(totalInfo, PlayerType.BATTER);
                        playerService.create(playerDto);
                    }
                    break;
                case 5:
                    System.out.println("수정할 선수 번호를 입력해 주세요.");
                    int upId = scanner.nextInt();

                    if (!isExist(upId - 1)) {
                        break;
                    }

                    List<String> updateInput = getPlayerDefaultInfo();

                    System.out.println("선수 포지션을 입력해 주세요. (1)투수/(2)타자");
                    String updatePosition = scanner.next();
                    if ("1".equals(updatePosition)) {
                        final List<String> totalInfo = getPitcherInfo(updateInput);
                        final PlayerDto playerDto = PlayerDto.create(upId - 1, totalInfo,
                                PlayerType.PITCHER);
                        playerService.update(playerDto);
                    }
                    if ("2".equals(updatePosition)) {
                        final List<String> totalInfo = getBatterInfo(updateInput);
                        final PlayerDto playerDto = PlayerDto.create(upId - 1, totalInfo,
                                PlayerType.BATTER);
                        playerService.update(playerDto);
                    }
                    break;
                case 6:
                    System.out.println("삭제할 선수 번호를 입력해 주세요.");
                    int delId = scanner.nextInt();

                    if (!isExist(delId - 1)) {
                        break;
                    }

                    playerService.delete(delId - 1);
                    break;
                case 7:
                    try {
                        playerService.save();
                    } catch (IOException e) {
                        System.out.println("파일 저장에 실패했습니다.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    flag = true;
                    break;
            }

            if (flag) {
                break;
            }
        }
    }

    private boolean isExist(int upId) {
        try {
            playerService.findBy(upId);
            return true;
        } catch (InValidDataException e) {
            System.out.println("해당하는 번호의 선수가 없습니다.");
            return false;
        }
    }

    private static void printPlayerInfo(Player player) {
        System.out.println(
                "(" + player.getId() + ") " + player.getName() + ", " + player.getAge()
                        + "세, " + player.getHeight() + "cm");
        if (player instanceof Pitcher) {
            final Pitcher pitcher = (Pitcher) player;
            System.out.println(
                    "\t 투수: 승 " + pitcher.getWins() + ", 패 " + pitcher.getLose()
                            + ", 방어율 " + pitcher.getEra() + "%");
        }
        if (player instanceof Batter) {
            final Batter batter = (Batter) player;
            System.out.println(
                    "\t 타자: 타수 " + batter.getBatCounts() + ", 안타수 " + batter.getHits()
                            + ", 타율 " + batter.getHitAvg() + "%");
        }
    }

    private List<String> getBatterInfo(List<String> input) {
        List<String> newList = new ArrayList<>(input);

        System.out.print("타수: ");
        String insertFour = scanner.next();
        newList.add(insertFour);

        System.out.print("안타: ");
        String insertFive = scanner.next();
        newList.add(insertFive);

        return newList;
    }

    private List<String> getPitcherInfo(List<String> input) {
        List<String> newList = new ArrayList<>(input);

        System.out.print("승: ");
        String updateFour = scanner.next();
        newList.add(updateFour);

        System.out.print("패: ");
        String updateFive = scanner.next();
        newList.add(updateFive);

        return newList;
    }

    private List<String> getPlayerDefaultInfo() {
        List<String> updateInput = new ArrayList<>();

        System.out.println("선수 정보를 입력해 주세요.");
        System.out.print("이름: ");
        String updateInfoOne = scanner.next();
        updateInput.add(updateInfoOne);

        System.out.print("나이: ");
        String updateInfoTwo = scanner.next();
        updateInput.add(updateInfoTwo);

        System.out.print("키: ");
        String updateInfoThree = scanner.next();
        updateInput.add(updateInfoThree);

        return Collections.unmodifiableList(updateInput);
    }
}
