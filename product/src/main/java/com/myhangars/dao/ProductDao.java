package com.myhangars.dao;


import com.myhangars.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductDao {
    public Page<Product> findAll(Pageable pageable);
    public Optional<Product> findById(long id);
    public Optional<Product> findByName(String name);
    public Product save(Product product);
    public Product update(Product product);
}
