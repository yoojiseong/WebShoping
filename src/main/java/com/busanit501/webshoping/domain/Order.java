package com.busanit501.webshoping.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블 이름 명시
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "orderItems") // 순환 참조 방지

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId") // PK
    private Long orderId;

    @Column(name = "memberId", nullable = false)
    private Long memberId;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private Boolean status = false;

    // 양방향 연관관계 설정 - 비주인
    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this); // FK 설정
    }
}
