package assignment1.entity;

public class CanHitEntity extends CanBeHitEntity implements Runnable {

    protected int strength;
    protected int attackSpeed;
    protected final Object Lock = new Object();
    protected Entity opponent;

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

    public int getStrength() {
        return strength;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setOpponent(Entity opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {}

    protected void attack() {}
}
