package assignment1.entity;

// 타격당할 수 있는 엔티티를 나타내는 클래스
// 체력(healthPoint)과 방어력(defensivePower) 속성만 가짐
public class CanBeHitEntity extends Entity {

    // 엔티티의 체력 값
    protected int healthPoint;

    // 엔티티의 방어력 값
    protected int defensivePower;

    // 동기화용 잠금 객체(lock)
    protected final Object lock = new Object();

    // 생성자 - id, 이름, 초기 체력, 방어력을 받아 초기화
    public CanBeHitEntity(int id, String name, int healthPoint, int defensivePower) {
        super(id, name);
        this.healthPoint = healthPoint;
        this.defensivePower = defensivePower;
    }

    // 체력 값을 반환 (동기화하여 여러 스레드 안전 보장)
    public int getHealthPoint() {
        synchronized (lock) {
            return healthPoint;
        }
    }

    // 방어력 값을 반환 (동기화하여 여러 스레드 안전 보장)
    public int getDefensivePower() {
        synchronized (lock) {
            return defensivePower;
        }
    }

    // 살아있는지 여부 반환 (체력이 0보다 크면 생존 상태)
    public boolean isAlive() {
        synchronized (lock) {
            return healthPoint > 0;
        }
    }

    // 체력 감소 메서드 (amount만큼 체력을 깎고 최소 0으로 제한, 동기화 처리)
    public void decreaseHealthPoint(int amount) {
        synchronized (lock) {
            healthPoint = Math.max(healthPoint - amount, 0);
        }
    }
}
