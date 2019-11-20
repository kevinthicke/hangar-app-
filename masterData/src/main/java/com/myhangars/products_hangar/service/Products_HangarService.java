package com.myhangars.products_hangar.service;

import com.myhangars.dto.ProductExtendedDto;
import com.myhangars.model.Product;
import com.myhangars.products_hangar.model.Products_Hangar;

import java.util.List;

public interface Products_HangarService {
    public List<Products_Hangar> getListProducts_HangarsByHangarId(long id);
    public List<Products_Hangar> getListProducts_HangarsByProductId(long id);
    public List<ProductExtendedDto> getHangarProductsByHangarId(long id);
    public List<ProductExtendedDto> getHangarProductByHangarName(String name);
    public Products_Hangar getHangarProduct(long hangarId, long productId);
    public Products_Hangar includeProductInHangarByHangarId(Product product, long hangarId, int quantity);
    public Products_Hangar includeProductInHangarByHangarIdAndProductId(long hangarId, long productId);
    public Products_Hangar setQuantityByHangarIdAndProductId(long hangarId, long productId, int quantity);
}
