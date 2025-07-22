package com.busanit501.webshoping.service;

import com.busanit501.webshoping.domain.CartItem;
import com.busanit501.webshoping.domain.Order;
import com.busanit501.webshoping.domain.OrderItem;
import com.busanit501.webshoping.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@Builder
@RequiredArgsConstructor
@Transactional()
public class OrderServicImpl implements OrderService {

    private final CartItemRepository cartItemRepository;

    @Override
    public void PurchaseFromCart(Long memberId) {
        List<CartItem> cartItems = cartItemRepository.findByMemberId(memberId);


        if(cartItems.isEmpty()){
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        Order order = Order.builder()
                .memberId(memberId)
                .status(false)
                .build();

        for(CartItem cart : cartItems){
            Product product = productRepository.findBtId(cart.getProduct())
                    .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

            OrderItem orderItem = OrderItem.builder()
                    .productId(cart.getProductId())
                    .quantity(cart.getQuantity())
                    .price(product.getPrice())
                    .build();

            order.addOrderItem(orderItem);
        }
        // 4. 주문 저장
        orderRepository.save(order);

        // 5. 장바구니 비우기
        cartRepository.deleteByMemberId(memberId);

    }
}
