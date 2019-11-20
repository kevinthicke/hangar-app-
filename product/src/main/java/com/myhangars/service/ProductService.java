package com.myhangars.service;


import com.myhangars.model.Price;
import com.myhangars.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
   public Page<Product> getAll(Pageable pageable);
   public Product getById(long id);
   public Product getByName(String name);
   public Product insert(Product product);
   public Product update(long id, Product product);
   public Product insertPrices(long id, List<Price> prices);
   public Product insertPrice(long id, float price);
   public Price getLastPrice(long productId);
}
