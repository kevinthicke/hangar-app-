package com.myhangars.products_hangar.dao;

import com.myhangars.products_hangar.model.Products_Hangar;

import java.util.List;
import java.util.Optional;

public interface Products_HangarDao {
    public List<Products_Hangar> findListProducts_HangarByHangarId(long id);
    public List<Products_Hangar> findListProducts_HangarByProductId(long id);
    public Optional<Products_Hangar> findProducts_Hangar(long hangarId, long productId);
    public Products_Hangar includeProductInHangar(Products_Hangar products_hangar);
    public Optional<Products_Hangar> findByHangarIdAndProductId(long hangarId, long productId);
    public Products_Hangar save(Products_Hangar products_hangar);
}


