package assignment1.entity;

public class MonsterEntity extends CanHitEntity {

    protected int value;
    protected Entity opponent;

    public MonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setOpponent(Entity opponent) {
        this.opponent = opponent;
    }

    public void run() {}

    protected void attack() {}
}
