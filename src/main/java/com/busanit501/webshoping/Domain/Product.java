package com.busanit501.webshoping.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity  // 이 클래스는 JPA가 관리하는 엔티티(테이블과 매핑되는 클래스)임
@Getter  // 롬복이 자동으로 getter 메서드를 생성해줌
@Setter  // 롬복이 자동으로 setter 메서드를 생성해줌
public class Product {

    @Id  // 이 필드는 테이블의 기본 키(primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키를 DB가 자동 생성 (AUTO_INCREMENT)
    private Long productId;  // 상품 고유 번호 (기본키)

    private String productName;  // 상품 이름

    private int price;  // 상품 가격

    private int stock;  // 상품 재고 수량

    private String productTag;  // 상품 태그 (예: '여름', '세일', '남성의류' 등)
}
