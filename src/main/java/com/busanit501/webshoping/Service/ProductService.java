package com.busanit501.webshoping.Service;  // 현재 클래스가 속한 패키지

import com.busanit501.webshoping.Domain.Product;  // 상품 엔티티 클래스 import
import com.busanit501.webshoping.Repository.ProductRepository;  // 상품 관련 DB 작업을 담당하는 리포지토리 import
import org.springframework.stereotype.Service;  // 해당 클래스가 서비스 컴포넌트임을 나타내는 어노테이션

// 이 클래스가 서비스 레이어임을 Spring에 알려줌 (스프링이 자동으로 객체를 생성해서 관리함)
@Service
public class ProductService {

    // 상품 데이터를 저장하고 불러오는 작업을 담당하는 Repository (의존성 주입 받음)
    private final ProductRepository productRepository;

    // 생성자: Spring이 ProductRepository 객체를 자동으로 넣어줌
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품을 저장하는 메서드 (Controller에서 호출함)
    public void saveProduct(Product product) {
        // 전달받은 Product 객체를 DB에 저장
        productRepository.save(product);
    }
}
