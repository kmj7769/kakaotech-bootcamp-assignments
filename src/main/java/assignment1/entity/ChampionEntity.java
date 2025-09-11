package assignment1.entity;

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

    public void buyItem(Item item) {}
}
