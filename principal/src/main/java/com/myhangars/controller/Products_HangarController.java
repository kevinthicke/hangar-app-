package com.myhangars.controller;


import com.myhangars.builder.ProductBuilder;
import com.myhangars.builder.ProductDtoBuilder;
import com.myhangars.dto.HangarDto;
import com.myhangars.dto.ProductDto;
import com.myhangars.dto.ProductExtendedDto;
import com.myhangars.model.Product;
import com.myhangars.service.ProductService;
import com.myhangars.products_hangar.model.Products_Hangar;
import com.myhangars.products_hangar.service.Products_HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class Products_HangarController {

    @Autowired
    private Products_HangarService products_hangarService;

    @Autowired
    private ProductService productService;

    /*
    @GetMapping("/products_hangar/hangar")
    public List<Products_Hangar> loadListProducts_HangarByHangarId(@RequestParam(value = "id", required = true) long id) {
        return this.products_hangarService.getListProducts_HangarsByHangarId(id);
    }
    */

    @GetMapping(value = "/products-hangar", params = { "hangar_id", "product_id"})
    public ResponseEntity<?> loadProducts_Hangar(@RequestParam("hangar_id") long hangarId,
                                                 @RequestParam("product_id") long productId) {
        return new ResponseEntity<Products_Hangar>(
                this.products_hangarService.getHangarProduct(hangarId, productId),
                HttpStatus.OK
        );
    }

   @GetMapping("/hangars/products/{id}")
   public ResponseEntity<?> loadHangarProductByHangarId(@PathVariable("id") long id) {

       List<ProductDto> productDtos = new ArrayList<ProductDto>();

       /*
       this.products_hangarService
               .getHangarProductsByHangarId(id)
               .forEach(product -> {
                   productDtos.add(new ProductDtoBuilder(product).getProductDto());
               });
        */

       return new ResponseEntity<List<ProductExtendedDto>>(
               this.products_hangarService
                       .getHangarProductsByHangarId(id),
               HttpStatus.OK
       );
   }

   @GetMapping("/hangars/products/by-name")
    public ResponseEntity<List> loadHangarProductByHangarName(@RequestParam("name") @NotEmpty String name) {

        return new ResponseEntity<List>(
                this.products_hangarService
                        .getHangarProductByHangarName(name),
                HttpStatus.OK
        );
   }

   /*
   @PostMapping("/hangars/{id}/products")
    public ResponseEntity<Products_Hangar> saveProductInHangarByHangarId(@PathVariable("id") @Min(1) long id,
                                                                    @RequestBody @Valid ProductDto productDto) {
       Product product = new ProductBuilder(productDto).getProduct();
       this.productService.insert(product);

        return new ResponseEntity<Products_Hangar>(
                this.products_hangarService.includeProductInHangarByHangarId(product, id),
                HttpStatus.CREATED
        );
   }
   */



   @PostMapping("/hangars/{id}/products")
   public ResponseEntity<Products_Hangar> saveProductInHangarByHangarId(@PathVariable("id") @Min(1) long id,
                                                                        @RequestBody @Valid ProductExtendedDto productExtendedDto) {
       // TODO: Crear un builder para esto :)
       ProductDto productDto = new ProductDto();

       productDto.setName(productExtendedDto.getName());
       productDto.setDescription(productExtendedDto.getDescription());
       productDto.setPrices(productExtendedDto.getPrices());

       Product product = new ProductBuilder(productDto).getProduct();
       this.productService.insert(product);

       int quantity = productExtendedDto.getQuantity();

       return new ResponseEntity<Products_Hangar>(
               this.products_hangarService.includeProductInHangarByHangarId(product, id, quantity),
               HttpStatus.CREATED
       );
   }

   @PostMapping(path = "/include")
    public ResponseEntity<?> includeProductInHangarByHangarIdAndProductId(@RequestParam(value = "hangar_id", required = true) long hangarId,
                                                                          @RequestParam(value = "product_id", required = true) long productId) {

       return new ResponseEntity<Products_Hangar>(
               this.products_hangarService.includeProductInHangarByHangarIdAndProductId(hangarId, productId),
               HttpStatus.OK);
   }

   @PostMapping(path = "/products/set-quantity", params = { "hangar_id", "product_id", "quantity"})
   public ResponseEntity<?> setQuantityByHangarIdAndProductId(@RequestParam(value = "hangar_id", required = true) long hangarId,
                                                              @RequestParam(value = "product_id", required = true) long productId,
                                                              @RequestParam(value = "quantity") int quantity) {
       return new ResponseEntity<Products_Hangar>(
               this.products_hangarService.setQuantityByHangarIdAndProductId(hangarId, productId, quantity),
               HttpStatus.CREATED
       );
   }
}
