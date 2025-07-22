package com.busanit501.webshoping.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId") // 컬럼명 명시
    private Long orderItemId;


    // 양방향 관계 설정: 연관관계의 주인 (FK 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    private Long productId; // camelCase

    private int quantity;


    private BigDecimal price;


    public void setOrder(Order order) {
        this.order = order;

        // 양방향이니까 Order에도 나(this)를 추가해줘야 함
        if (!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }
}
