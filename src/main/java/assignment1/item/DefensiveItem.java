package assignment1.item;

// 방어력과 체력을 증가시키는 방어 아이템 클래스
// Item 클래스를 상속받음
public class DefensiveItem extends Item {

    // 체력 증가 값
    private int healthPointIncrease;

    // 방어력 증가 값
    private int defensivePowerIncrease;

    // 생성자 - 아이템 가격과 체력, 방어력 증가값을 받아 초기화
    public DefensiveItem(int price, int healthPointIncrease, int defensivePowerIncrease) {
        super(price);  // 상위 클래스 Item의 생성자 호출하여 가격 초기화
        this.healthPointIncrease = healthPointIncrease;
        this.defensivePowerIncrease = defensivePowerIncrease;
    }

    // 체력 증가량 반환
    public int getHealthPointIncrease() {
        return healthPointIncrease;
    }

    // 방어력 증가량 반환
    public int getDefensivePowerIncrease() {
        return defensivePowerIncrease;
    }
}
