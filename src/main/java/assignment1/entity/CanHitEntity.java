package assignment1.entity;

// 타격을 입힐 수 있는 엔티티 (CanBeHitEntity 상속)이며,
// Runnable 인터페이스를 구현하여 스레드로 동작 가능
public class CanHitEntity extends CanBeHitEntity implements Runnable {

    // 공격력
    protected int strength;

    // 공격 속도(밀리초 단위, 공격 간 딜레이)
    protected int attackSpeed;

    // 공격 대상 (상대 엔티티)
    protected CanBeHitEntity opponent;

    // 생성자 - id, 이름, 체력, 방어력, 공격력, 공격속도를 받아 초기화
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

    // 현재 공격력 반환 (동기화 처리)
    public int getStrength() {
        synchronized (lock) {
            return strength;
        }
    }

    // 현재 공격 속도 반환 (동기화 처리)
    public int getAttackSpeed() {
        synchronized (lock) {
            return attackSpeed;
        }
    }

    // 공격 대상 엔티티 설정
    public void setOpponent(CanBeHitEntity opponent) {
        this.opponent = opponent;
    }

    // 스레드 실행 메서드 (Runnable 구현)
    // 자신과 상대가 살아 있는 동안 반복적으로 공격 수행
    @Override
    public void run() {
        while (this.isAlive() && opponent.isAlive()) {
            attack(); // 공격 수행
            try {
                // 공격 속도(딜레이) 만큼 대기
                Thread.sleep(this.getAttackSpeed());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 실제 공격 처리 메서드
    // 상대가 살아있으면 상대 체력 감소
    protected void attack() {
        if (opponent.isAlive()) {
            opponent.decreaseHealthPoint(strength);
        }
    }
}
