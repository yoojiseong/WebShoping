package com.busanit501.webshoping.Controller;  // 현재 파일이 속한 패키지

// 필요한 클래스 import
import com.busanit501.webshoping.Domain.Product;  // 상품 도메인 (엔티티)
import com.busanit501.webshoping.Service.ProductService;  // 상품 관련 로직을 처리하는 서비스
import org.springframework.stereotype.Controller;  // 이 클래스가 웹 요청을 처리하는 컨트롤러임을 나타냄
import org.springframework.ui.Model;  // 뷰에 데이터를 전달할 때 사용하는 객체
import org.springframework.web.bind.annotation.*;  // 요청 매핑 관련 어노테이션들 (Get/PostMapping 등)

// 이 클래스가 Spring MVC의 컨트롤러임을 명시
@Controller
// 모든 요청 URL이 /admin/products 로 시작할 때 이 컨트롤러에서 처리함
@RequestMapping("/admin/products")
public class ProductController {

    // 상품 관련 로직을 처리할 서비스 객체를 주입받음 (생성자 주입 방식)
    private final ProductService productService;

    // 생성자: Spring이 ProductService를 자동으로 넣어줌 (DI - 의존성 주입)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록 폼을 보여주는 메서드 (GET 방식)
    @GetMapping("/new")
    public String showForm(Model model) {
        // 뷰(form)에서 사용할 빈 Product 객체를 모델에 담아 전달
        model.addAttribute("product", new Product());
        // 템플릿 이름 "product_form.html"을 반환 → 뷰 렌더링
        return "product_form";
    }

    // 상품 등록을 처리하는 메서드 (POST 방식)
    @PostMapping("/new")
    public String saveProduct(@ModelAttribute Product product) {
        // 사용자가 폼에 입력한 상품 정보를 저장
        productService.saveProduct(product);
        // 저장이 끝나면 상품 목록 페이지로 리다이렉트
        return "redirect:/admin/products";
    }
}
