package assignment1.entity;

import assignment1.item.AttackItem;
import assignment1.item.DefensiveItem;
import assignment1.item.Item;

public class ChampionEntity extends CanHitEntity {

    private int gold;

    public ChampionEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int gold) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    protected void setGold(int gold) {
        this.gold = gold;
    }

    public void buyItem(Item item) {
        if (item instanceof AttackItem) {
            strength += ((AttackItem) item).getStrengthIncrease();
            attackSpeed = ((AttackItem) item).getAttackSpeedChange();
        }
        else if (item instanceof DefensiveItem) {
            healthPoint += ((DefensiveItem) item).getHealthPointIncrease();
            defensivePower += ((DefensiveItem) item).getDefensivePowerIncrease();
        }
        gold -= item.getPrice();
    }

    @Override
    public void run() {
        int initHealthPoint = this.getHealthPoint();
        super.run();
        if (this.isAlive()) {
            System.out.println(name + " defeat" + opponent.getName() + "!");
            gold += ((MonsterEntity) opponent).getValue();
            healthPoint = initHealthPoint;
        }
        else {
            System.out.println(name + " defeated.");
        }
    }
}
