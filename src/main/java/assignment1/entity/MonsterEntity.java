package assignment1.entity;

public class MonsterEntity extends CanHitEntity {

    protected int value;

    public MonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        int initHealthPoint = this.getHealthPoint();
        super.run();
        healthPoint = initHealthPoint;
    }
}
