package assignment1.entity;

public class CanHitEntity extends CanBeHitEntity {

    protected int strength;
    protected int attackSpeed;
    protected final Object Lock = new Object();

    public CanHitEntity(int id,
                        String name,
                        int healthPoint,
                        int defensivePower,
                        int strength,
                        int attackSpeed) {
        super(id, name, healthPoint, defensivePower);
        this.strength = strength;
        this.attackSpeed = attackSpeed;
    }

    public CanHitEntity(int id,
                        int healthPoint,
                        int defensivePower,
                        int strength,
                        int attackSpeed) {
        super(id, healthPoint, defensivePower);
        this.strength = strength;
        this.attackSpeed = attackSpeed;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
