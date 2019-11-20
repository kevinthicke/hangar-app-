package com.myhangars.products_hangar.repository;


import com.myhangars.products_hangar.model.Products_Hangar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Products_HangarRepository extends JpaRepository<Products_Hangar, Long> {

    @Query(value = "SELECT ph FROM Products_Hangar ph WHERE ph.hangar_id = ?1")
    List<Products_Hangar> findByHangar_id(long id);

    @Query(value = "SELECT ph FROM Products_Hangar ph WHERE ph.product_id = ?1")
    List<Products_Hangar> findByProduct_Id(long id);

    @Query(value = "SELECT ph FROM Products_Hangar ph WHERE ph.hangar_id = ?1 AND ph.product_id = ?2")
    Optional<Products_Hangar> findByHangar_IdAndProduct_Id(long hangarId, long productId);

}
