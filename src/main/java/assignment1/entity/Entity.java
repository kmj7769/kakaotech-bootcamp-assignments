package assignment1.entity;

// 모든 엔티티의 기본 속성을 가지는 상위 클래스
public class Entity {

    // 엔티티 고유 식별자
    protected int id;

    // 엔티티 이름
    protected String name;

    // 생성자 - id와 이름을 받아 초기화
    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 엔티티의 id 반환
    public int getId() {
        return id;
    }

    // 엔티티의 이름 반환
    public String getName() {
        return name;
    }
}
