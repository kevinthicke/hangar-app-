package com.myhangars.repository;


import com.myhangars.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select product FROM Product product WHERE product.name = ?1 AND product.state = true")
    public Optional<Product> findByNameWithTrueState(String name);

    @Query("Select product FROM Product product WHERE product.state = true")
    public Page<Product> findAllWithTrueState(Pageable pageable);

    @Query("Select product From Product product WHERE product.state = true AND product.id = ?1")
    public Optional<Product> findByIdWithTrueState(long id);

}
