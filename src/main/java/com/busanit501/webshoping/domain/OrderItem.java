package com.busanit501.webshoping.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id") // 컬럼명 명시
    private Long order_item_id; // Java에서는 camelCase를 사용하는 것이 일반적입니다.


    // 양방향 관계 설정: 연관관계의 주인 (FK 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productid; // camelCase


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
