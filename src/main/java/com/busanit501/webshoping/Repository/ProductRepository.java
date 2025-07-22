package com.busanit501.webshoping.Repository;

import com.busanit501.webshoping.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 기본 CRUD 제공됨 (save, findById, delete 등)
}
