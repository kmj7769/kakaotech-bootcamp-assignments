package assignment1.item;

// 공격력을 증가시키고 공격 속도를 변경하는 공격 아이템 클래스
// Item 클래스를 상속받음
public class AttackItem extends Item {
    // 공격력 증가 값
    private int strengthIncrease;

    // 공격 속도 변화 값 (밀리초 단위, 공격 속도의 증가 또는 감소)
    private int attackSpeedChange;

    // 생성자 - 아이템 가격, 공격력 증가량, 공격속도 변화를 받아 초기화
    public AttackItem(int price, int strengthIncrease, int attackSpeedChange) {
        super(price);  // 상위 클래스의 생성자 호출하여 가격 초기화
        this.strengthIncrease = strengthIncrease;
        this.attackSpeedChange = attackSpeedChange;
    }

    // 공격력 증가량 반환
    public int getStrengthIncrease() {
        return strengthIncrease;
    }

    // 공격 속도 변화량 반환
    public int getAttackSpeedChange() {
        return attackSpeedChange;
    }
}
