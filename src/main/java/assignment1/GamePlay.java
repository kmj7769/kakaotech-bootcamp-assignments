package assignment1;

import assignment1.entity.*;
import assignment1.item.AttackItem;
import assignment1.item.BackGroundMusicPlay;
import assignment1.item.DefensiveItem;
import assignment1.item.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 게임의 메인 실행 클래스
// 콘솔 기반으로 챔피언 캐릭터를 조작하며 몬스터와 싸우고 아이템을 구매할 수 있는 간단한 게임플레이 구현
public class GamePlay {
    // 메인 메뉴 선택지 배열
    private final static String[] choices = {"1. Fight", "2. Buy Items", "3. Leave the Game"};

    // 전투 선택지 배열
    private final static String[] battleChoices = {"1. Small Monster", "2. Big Monster", "3. Boss Monster", "4. Back to Menu"};

    // 아이템 구매 메뉴 선택지 배열
    private final static String[] itemChoices = {"1. Armor", "2. Sword", "3. Axe", "4. Back to Menu"};

    // BGM 목록 배열
    private final static String[] bgmChoices = {
            "Peaceful Theme: Game Menu BGM",
            "Majestic Theme: Battle BGM",
            "Dark and Heavy Theme: Boss Room BGM",
            "Bright and Powerful Theme: Monster Defeated BGM",
            "Bright and Holy Theme: Boss Defeated BGM",
            "Calm Theme: Shop BGM"
    };

    public static void main(String[] args) throws InterruptedException, IOException {
        int cnt = 1;
        // 몬스터, 보스 몬스터 생성 및 배열에 저장
        MonsterEntity monster1 = new MonsterEntity(cnt++, "monster1", 100, 5, 10, 1500, 400);
        MonsterEntity monster2 = new MonsterEntity(cnt++, "monster2", 200, 7, 12, 700, 800);
        BossMonsterEntity boss = new BossMonsterEntity(cnt++, "bossMonster", 1000, 10, 20, 2000, 2000);
        MonsterEntity[] monsterArray = {monster1, monster2, boss};

        // 아이템 생성 및 배열 저장
        DefensiveItem defItem = new DefensiveItem(400, 200, 5);
        AttackItem atkItem1 = new AttackItem(400, 5, 900);
        AttackItem atkItem2 = new AttackItem(800, 12, 1000);
        Item[] itemArray = {defItem, atkItem1, atkItem2};

        BackGroundMusicPlay backGroundMusicPlay = new BackGroundMusicPlay();
        Thread backGroundMusicThread = new Thread(backGroundMusicPlay);
        backGroundMusicThread.start();

        // 사용자 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter your champion name: ");
        String championName = br.readLine();

        // 챔피언 생성 (초기 골드는 0)
        ChampionEntity champion = new ChampionEntity(1, championName, 300, 5, 15, 1000, 0);

        System.out.println("Welcome to Console Battle World!");

        int turn = 1;

        // 챔피언이 살아있는 동안 게임 진행 반복
        while (champion.isAlive()) {

            backGroundMusicPlay.setMusic(bgmChoices[0]);

            printChoices(choices, turn);  // 메인 메뉴 출력

            System.out.println("Please enter your choice: ");
            int choice = readInt(br);     // 사용자 선택 입력
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {  // 전투 선택
                printChoices(battleChoices, turn);  // 전투 메뉴 출력

                System.out.println("Please select your opponent: ");
                int battleChoice = readInt(br);    // 상대 몬스터 선택
                if (battleChoice == -1) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                if (battleChoice == 4) break;     // 뒤로 가기 선택시 메인으로

                if (battleChoice < 1 || battleChoice > monsterArray.length) {
                    System.out.println("Invalid choice. Please select a valid opponent.");
                    continue;
                }

                // 챔피언과 선택한 몬스터간 상호 상대 설정
                champion.setOpponent(monsterArray[battleChoice - 1]);
                monsterArray[battleChoice - 1].setOpponent(champion);

                // 두 엔티티 스레드 생성 및 시작
                Thread championThread = new Thread(champion);
                Thread opponentThread = new Thread(monsterArray[battleChoice - 1]);

                championThread.start();
                opponentThread.start();

                if (battleChoice == 3) backGroundMusicPlay.setMusic(bgmChoices[2]);
                else backGroundMusicPlay.setMusic(bgmChoices[1]);

                // 두 스레드가 종료될 때까지 대기
                championThread.join();
                opponentThread.join();

                System.out.println("Battle Over");

                // 챔피언 사망 시 게임 종료
                if (!champion.isAlive()) {
                    System.out.println("Game Over...");
                    break;
                }

                backGroundMusicPlay.setMusic(bgmChoices[3]);

                // 보스 몬스터 처치 시 게임 클리어 메시지 및 종료
                if (battleChoice == 3) {
                    backGroundMusicPlay.setMusic(bgmChoices[4]);
                    System.out.println("Game Clear!");
                    break;
                }

                turn++;
            }
            else if (choice == 2) {  // 아이템 구매 메뉴
                backGroundMusicPlay.setMusic(bgmChoices[5]);

                while (true) {
                    printItemChoices(itemArray);   // 아이템 목록 출력

                    System.out.println("You have " + champion.getGold() + " gold!");

                    System.out.println("Please select an item you want to buy: ");
                    int itemChoice = readInt(br);
                    if (itemChoice == -1) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }
                    if (itemChoice == itemChoices.length) break;  // 뒤로 가기

                    if (itemChoice < 1 || itemChoice > itemArray.length) {
                        System.out.println("Invalid item choice. Please select a valid item.");
                        continue;
                    }

                    Item selectedItem = itemArray[itemChoice - 1];
                    if (selectedItem.getPrice() > champion.getGold()) {
                        System.out.println("You can't buy this item!");
                    }
                    else {
                        champion.buyItem(selectedItem);
                    }
                }
            }
            else if (choice == 3) {  // 게임 종료 선택
                System.out.println("Thanks for playing!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        backGroundMusicPlay.stopMusic();
        backGroundMusicThread.join();
    }

    // 메뉴 선택지 출력 (초기 턴엔 첫 선택지만, 이후엔 모두 출력)
    private static void printChoices(String[] choices, int turn) {
        if (turn == 1) {
            System.out.println(choices[0]);
        } else {
            for (String choice : choices) {
                System.out.print(choice + " ");
            }
            System.out.println();
        }
    }

    // 아이템 목록과 가격 출력
    private static void printItemChoices(Item[] itemArray) {
        for (int i = 0; i < GamePlay.itemChoices.length -1; i++) {
            System.out.println(GamePlay.itemChoices[i] + ": " + itemArray[i].getPrice() + " gold");
        }
        System.out.println(GamePlay.itemChoices[GamePlay.itemChoices.length -1]);
    }

    // 입력된 문자열을 정수로 변환, 실패 시 -1 반환
    private static int readInt(BufferedReader br) {
        try {
            String input = br.readLine();
            return Integer.parseInt(input.trim());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }
}
