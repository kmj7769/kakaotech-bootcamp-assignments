package assignment1.entity;

public class BossMonsterEntity extends MonsterEntity {

    public BossMonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed, value);
    }

    public void bossDefeated() {
        System.out.println("Boss " + this.name + " defeated!");
    }

    @Override
    public void run() {
        super.run();
        bossDefeated();
    }
}
