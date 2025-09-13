package assignment1.item;

// 게임 아이템의 기본 속성을 가지는 상위 클래스
public class Item {

    // 아이템 가격
    protected int price;

    // 생성자 - 아이템 가격을 받아 초기화
    public Item(int price) {
        this.price = price;
    }

    // 아이템의 가격 반환
    public int getPrice() {
        return price;
    }
}
