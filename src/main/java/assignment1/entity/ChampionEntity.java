package assignment1.entity;

import assignment1.item.AttackItem;
import assignment1.item.DefensiveItem;
import assignment1.item.Item;

// 플레이어 캐릭터 역할을 하는 챔피언 엔티티 클래스
// CanHitEntity를 상속받아 공격, 공격 속도, 체력 등 기본 기능 보유
public class ChampionEntity extends CanHitEntity {

    // 보유 골드 (게임 내 재화)
    private int gold;

    // 생성자 - id, 이름, 체력, 방어력, 공격력, 공격속도, 초기 골드를 받아 초기화
    public ChampionEntity(int id, String name, int healthPoint, int defensivePower, int strength, int attackSpeed, int gold) {
        super(id, name, healthPoint, defensivePower, strength, attackSpeed);
        this.gold = gold;
    }

    // 보유 골드 조회
    public int getGold() {
        return gold;
    }

    // 골드 설정 (외부 접근 제한, 내부 강화용으로 사용)
    protected void setGold(int gold) {
        this.gold = gold;
    }

    // 아이템 구매 처리 메서드
    // 공격 아이템이면 공격력 증가 및 공격속도 변경
    // 방어 아이템이면 체력과 방어력 증가
    // 구매 후 골드 차감
    public void buyItem(Item item) {
        if (item instanceof AttackItem) {
            strength += ((AttackItem) item).getStrengthIncrease();
            attackSpeed = ((AttackItem) item).getAttackSpeedChange();
        }
        else if (item instanceof DefensiveItem) {
            healthPoint += ((DefensiveItem) item).getHealthPointIncrease();
            defensivePower += ((DefensiveItem) item).getDefensivePowerIncrease();
        }
        gold -= item.getPrice();
    }

    // 스레드 실행 메서드 (Runnable 구현)
    // 부모 클래스의 run() 호출하여 전투 진행 후,
    // 전투 승리 시 상대 몬스터 처치 보상 골드 지급 및 체력 회복 처리
    @Override
    public void run() {
        // 전투 시작 전 초기 체력 저장
        int initHealthPoint = this.getHealthPoint();

        // 부모 클래스의 run 실행 (반복 공격)
        super.run();

        // 전투 결과 출력 및 처리
        if (this.isAlive()) {
            System.out.println(name + " defeat " + opponent.getName() + "!");
            // 상대 몬스터의 value(보상)를 얻어 골드 증가
            gold += ((MonsterEntity) opponent).getValue();
            // 체력 초기 상태로 회복
            healthPoint = initHealthPoint;
        }
        else {
            System.out.println(name + " defeated.");
        }
    }
}
