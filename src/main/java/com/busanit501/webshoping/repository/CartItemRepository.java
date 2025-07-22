package com.busanit501.webshoping.repository;

import com.busanit501.webshoping.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select b from CartItem b where b.memberId = :memberId")
    List<CartItem> findByMemberId(@Param("memberId")Long memberId);
}
