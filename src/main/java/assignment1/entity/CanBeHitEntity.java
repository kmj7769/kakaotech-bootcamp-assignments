package assignment1.entity;

public class CanBeHitEntity extends Entity {

    protected int healthPoint;
    protected int defensivePower;
    protected final Object Lock = new Object();

    public CanBeHitEntity(int id, String name, int healthPoint, int defensivePower) {
        super(id, name);
        this.healthPoint = healthPoint;
        this.defensivePower = defensivePower;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getDefensivePower() {
        return defensivePower;
    }

    public boolean isAlive() {
        return true;
    }

    public void decreaseHealthPoint() {}
}
