package assignment1.entity;

public class BuffMonsterEntity extends MonsterEntity {

    public BuffMonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed, value);
    }

    public void giveBuff() {}
}
