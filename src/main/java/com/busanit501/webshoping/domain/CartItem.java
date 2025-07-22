package com.busanit501.webshoping.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 ID (연관관계 매핑도 가능하지만, 단순 Long으로 두는 경우 많음)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    // 상품 ID
    @Column(name = "product_id", nullable = false)
    private Long productId;

    // 수량
    @Column(nullable = false)
    private int quantity;
}
