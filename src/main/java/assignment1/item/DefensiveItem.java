package assignment1.item;

public class DefensiveItem extends Item {

    private int healthPointIncrease;
    private int defensivePowerIncrease;

    public DefensiveItem(int price, int healthPointIncrease, int defensivePowerIncrease) {
        super(price);
        this.healthPointIncrease = healthPointIncrease;
        this.defensivePowerIncrease = defensivePowerIncrease;
    }

    public int getHealthPointIncrease() {
        return healthPointIncrease;
    }

    public int getDefensivePowerIncrease() {
        return defensivePowerIncrease;
    }
}
