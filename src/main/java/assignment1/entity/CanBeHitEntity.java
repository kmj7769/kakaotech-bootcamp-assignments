package assignment1.entity;

public class CanBeHitEntity extends Entity {

    protected int healthPoint;
    protected int defensivePower;
    protected final Object lock = new Object();

    public CanBeHitEntity(int id, String name, int healthPoint, int defensivePower) {
        super(id, name);
        this.healthPoint = healthPoint;
        this.defensivePower = defensivePower;
    }

    public int getHealthPoint() {
        synchronized (lock) {
            return healthPoint;
        }
    }

    public int getDefensivePower() {
        synchronized (lock) {
            return defensivePower;
        }
    }

    public boolean isAlive() {
        synchronized (lock) {
            return healthPoint > 0;
        }
    }

    public void decreaseHealthPoint(int amount) {
        synchronized (lock) {
             healthPoint = Math.max(healthPoint - amount, 0);
        }
    }
}
