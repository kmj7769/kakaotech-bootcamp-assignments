package assignment1.entity;

public class MonsterEntity extends CanHitEntity implements Runnable {

    protected int value;

    public MonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {}

    protected void attack() {}
}
