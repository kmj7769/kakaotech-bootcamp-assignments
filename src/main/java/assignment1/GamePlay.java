package assignment1;

import assignment1.entity.*;
import assignment1.item.AttackItem;
import assignment1.item.DefensiveItem;
import assignment1.item.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GamePlay {
    private final static String[] choices = {"1. Fight", "2. Buy Items", "3. Leave the Game"};
    private final static String[] battleChoices = {"1. Small Monster", "2. Big Monster", "3. Boss Monster", "4. Back to Menu"};
    private final static String[] itemChoices = {"1. Armor", "2. Sword", "3. Axe", "4. Back to Menu"};

    public static void main(String[] args) throws InterruptedException, IOException {
        int cnt = 1;
        MonsterEntity monster1 = new MonsterEntity(cnt++, "monster1", 100, 5, 10, 1500, 400);
        MonsterEntity monster2 = new MonsterEntity(cnt++, "monster2", 200, 7, 12, 700, 800);
        BossMonsterEntity boss = new BossMonsterEntity(cnt++, "bossMonster", 1000, 10, 20, 2000, 2000);
        MonsterEntity[] monsterArray = {monster1, monster2, boss};

        DefensiveItem defItem = new DefensiveItem(400, 200, 5);
        AttackItem atkItem1 = new AttackItem(400, 5, 900);
        AttackItem atkItem2 = new AttackItem(800, 12, 1000);
        Item[] itemArray = {defItem, atkItem1, atkItem2};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter your champion name: ");
        String championName = br.readLine();

        ChampionEntity champion = new ChampionEntity(1, championName, 300, 5, 15, 1000, 0);

        System.out.println("Welcome to Console Battle World!");
        int turn = 1;
        while (champion.isAlive()) {
            printChoices(choices, turn);

            System.out.print("Please enter your choice: ");
            int choice = readInt(br);
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                printChoices(battleChoices, turn);

                System.out.print("Please select your opponent: ");
                int battleChoice = readInt(br);
                if (battleChoice == -1) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                if (battleChoice == 4) break;

                if (battleChoice < 1 || battleChoice > monsterArray.length) {
                    System.out.println("Invalid choice. Please select a valid opponent.");
                    continue;
                }

                Thread championThread = new Thread(champion);
                Thread opponentThread = new Thread(monsterArray[battleChoice - 1]);

                champion.setOpponent(monsterArray[battleChoice - 1]);
                monsterArray[battleChoice - 1].setOpponent(champion);

                championThread.start();
                opponentThread.start();

                championThread.join();
                opponentThread.join();

                System.out.println("Battle Over");

                if (!champion.isAlive()) {
                    System.out.println("Game Over...");
                    break;
                }

                if (battleChoice == 3) {
                    System.out.println("Game Clear!");
                    break;
                }

                turn++;
            }
            else if (choice == 2) {
                while (true) {
                    printItemChoices(itemArray);

                    System.out.println("You have " + champion.getGold() + " gold!");

                    System.out.print("Please select an item you want to buy: ");
                    int itemChoice = readInt(br);
                    if (itemChoice == -1) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }
                    if (itemChoice == itemChoices.length) break;

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
            else if (choice == 3) {
                System.out.println("Thanks for playing!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

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

    private static void printItemChoices(Item[] itemArray) {
        for (int i = 0; i < GamePlay.itemChoices.length -1; i++) {
            System.out.println(GamePlay.itemChoices[i] + ": " + itemArray[i].getPrice() + " gold");
        }
        System.out.println(GamePlay.itemChoices[GamePlay.itemChoices.length -1]);
    }

    private static int readInt(BufferedReader br) {
        try {
            String input = br.readLine();
            return Integer.parseInt(input.trim());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }
}
