package assignment1.entity;

public class CanHitEntity extends CanBeHitEntity implements Runnable {

    protected int strength;
    protected int attackSpeed;
    protected CanBeHitEntity opponent;

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
        synchronized (lock) {
            return strength;
        }
    }

    public int getAttackSpeed() {
        synchronized (lock) {
            return attackSpeed;
        }
    }

    public void setOpponent(CanBeHitEntity opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {
        while (this.isAlive() && opponent.isAlive()) {
            attack();
            try {
                Thread.sleep(this.getAttackSpeed());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected void attack() {
        if (opponent.isAlive()) {
            opponent.decreaseHealthPoint(strength);
        }
    }
}
