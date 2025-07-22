package com.busanit501.webshoping.Service;

import com.busanit501.webshoping.Entity.Product; // Product 엔티티 import
import com.busanit501.webshoping.Repository.ProductRepository; // ProductRepository import
import com.busanit501.webshoping.RequestDto.ProductRequestDto; // 요청 DTO import
import com.busanit501.webshoping.ResponseDto.ProductResponseDto; // 응답 DTO import
import jakarta.transaction.Transactional; // 트랜잭션 처리를 위한 어노테이션
import lombok.RequiredArgsConstructor; // final 필드에 대한 생성자를 자동으로 만들어줘.
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service; // 이 클래스가 서비스 계층임을 명시

import java.util.List;
import java.util.stream.Collectors; // 리스트 스트림 처리용

@Log4j2
@Service // "나 서비스 계층이야! 비즈니스 로직은 내가 다 할게!" 라고 스프링에게 알려줘.
@RequiredArgsConstructor // final이 붙은 필드(productRepository)를 파라미터로 받는 생성자를 롬복이 자동으로 만들어줘.
@Transactional // 메서드가 실행되는 동안 데이터베이스 작업이 하나의 트랜잭션으로 묶이도록 해줘.
// 롤에서 스킬 콤보 넣을 때 중간에 끊기면 안 되듯이, DB 작업도 한 번에 성공하거나, 실패하면 전부 롤백돼야 할 때 이걸 써.
public class ProductService {

    private final ProductRepository productRepository; // ProductRepository를 주입받아 사용.

    // ✅ 상품 등록 (Create)
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        // DTO를 엔티티로 변환
        Product product = new Product();
        product.setProductName(requestDto.getProductName());
        product.setPrice(requestDto.getPrice());
        product.setStock(requestDto.getStock());
        product.setProductTag(requestDto.getProductTag());

        log.info("공동 작업 테스트중");
        log.info("공동 작업 테스트중(민주현)");
        // 리포지토리를 통해 DB에 저장
        Product savedProduct = productRepository.save(product); // save()는 저장 후 저장된 엔티티를 반환해줘.

        // 저장된 엔티티를 응답 DTO로 변환하여 반환
        return new ProductResponseDto(savedProduct);
    }

    // ✅ 전체 상품 조회 (Read All)
    public List<ProductResponseDto> getAllProducts() {
        // 모든 상품 엔티티를 DB에서 가져옴
        List<Product> products = productRepository.findAll();

        // 엔티티 리스트를 스트림을 이용해서 DTO 리스트로 변환
        // 롤에서 모든 아군 챔피언 정보를 한번에 쫙 보는 느낌!
        return products.stream()
                .map(ProductResponseDto::new) // 각 Product 엔티티를 ProductResponseDto로 변환
                .collect(Collectors.toList()); // 변환된 DTO들을 리스트로 다시 묶어줘.
    }

    // ✅ 특정 상품 조회 (Read One)
    public ProductResponseDto getProduct(Long id) {
        // ID를 통해 상품 엔티티를 DB에서 조회.
        // .orElseThrow()는 만약 해당 ID의 상품이 없으면 예외를 발생시키겠다는 의미야.
        // "이 챔피언 찾을 수 없음!" 같은 거지.
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id: " + id));

        // 조회된 엔티티를 응답 DTO로 변환하여 반환
        return new ProductResponseDto(product);
    }

    // ✅ 상품 수정 (Update)
    // @Transactional 어노테이션 덕분에 이 메서드 안에서 엔티티 객체를 변경하면
    // 메서드가 끝날 때 변경된 내용이 자동으로 DB에 반영돼! (더티 체킹)
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        // 먼저 수정할 상품을 DB에서 찾아와.
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id: " + id));

        // DTO의 정보로 엔티티의 필드를 업데이트.
        product.setProductName(requestDto.getProductName());
        product.setPrice(requestDto.getPrice());
        product.setStock(requestDto.getStock());
        product.setProductTag(requestDto.getProductTag());

        // (선택사항) 명시적으로 save를 호출해도 되지만, @Transactional 덕분에 생략 가능해.
        // productRepository.save(product);

        // 업데이트된 엔티티를 응답 DTO로 변환하여 반환.
        return new ProductResponseDto(product);
    }

    // ✅ 상품 삭제 (Delete)
    public void deleteProduct(Long id) {
        // 먼저 삭제할 상품이 존재하는지 확인해.
        if (!productRepository.existsById(id)) { // existsById()는 존재 여부만 빠르게 확인해줘.
            throw new IllegalArgumentException("삭제할 상품을 찾을 수 없습니다. id: " + id);
        }
        // 리포지토리를 통해 DB에서 상품 삭제
        productRepository.deleteById(id);
    }
}