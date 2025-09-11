package assignment1.item;

public class AttackItem extends Item {
    private int strengthIncrease;
    private int attackSpeedChange;

    public AttackItem(int price, int strengthIncrease, int attackSpeedChange) {
        super(price);
        this.strengthIncrease = strengthIncrease;
        this.attackSpeedChange = attackSpeedChange;
    }

    public int getStrengthIncrease() {
        return strengthIncrease;
    }

    public int getAttackSpeedChange() {
        return attackSpeedChange;
    }
}
