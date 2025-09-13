package assignment1.entity;

// 몬스터 역할을 하는 엔티티 클래스
// CanHitEntity를 상속받아 공격 기능 등 기본 능력 보유
public class MonsterEntity extends CanHitEntity {

    // 몬스터를 처치했을 때 획득하는 재화 값
    protected int value;

    // 생성자 - id, 이름, 체력, 방어력, 공격력, 공격속도, 처치 시 획득 재화를 받아 초기화
    public MonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.value = value;
    }

    // 처치 시 획득할 재화 반환
    public int getValue() {
        return value;
    }

    // 스레드 실행 메서드 (Runnable 구현)
    // 전투 진행 후 체력을 초기 상태로 회복
    @Override
    public void run() {
        // 전투 시작 전 초기 체력 저장
        int initHealthPoint = this.getHealthPoint();

        // 부모 클래스의 run 실행 = 전투 반복 수행
        super.run();

        // 전투 종료 후 체력 초기 상태로 회복 (재사용 가능하도록)
        healthPoint = initHealthPoint;
    }
}
