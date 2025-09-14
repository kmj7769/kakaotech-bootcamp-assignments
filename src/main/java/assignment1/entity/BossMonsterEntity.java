package assignment1.entity;

// 보스 몬스터를 나타내는 클래스
// MonsterEntity를 상속받아 보스 몬스터 특화 기능 보유
public class BossMonsterEntity extends MonsterEntity {

    // 생성자 - id, 이름, 체력, 방어력, 공격력, 공격속도, 처치 시 획득 재화 값을 받아 초기화
    public BossMonsterEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int value) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed, value);
    }

    // 보스 처치 시 출력 메시지 메서드
    public void bossDefeated() {
        System.out.println("Boss " + this.name + " defeated!");
    }

    // 스레드 실행 메서드 (Runnable 구현)
    // 기존 전투 수행 후 보스 처치 메시지 출력
    @Override
    public void run() {
        super.run(); // 부모 클래스의 run() 호출해 전투 진행
        if (!this.isAlive()) bossDefeated(); // 전투 승리 후 보스 처치 알림
    }
}
