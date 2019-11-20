package com.myhangars.order.service;

import com.myhangars.config.JwtTokenUtil;
import com.myhangars.exception.OrderException;
import com.myhangars.model.UserEntity;
import com.myhangars.model.UserProfile;
import com.myhangars.order.dao.OrderDao;
import com.myhangars.order.model.Order;
import com.myhangars.product_order.model.Product_Order;
import com.myhangars.product_order.service.Product_OrderService;
import com.myhangars.products_hangar.service.Products_HangarService;
import com.myhangars.service.ProductService;
import com.myhangars.user.service.UserEntityService;
import com.myhangars.user_profile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Product_OrderService product_orderService;

    @Autowired
    private Products_HangarService products_hangarService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Order> getAll() {
        return this.orderDao.findAll();
    }

    @Override
    public List<Order> getAllByUserToken(String userToken) {
        String username = jwtTokenUtil.getUsernameFromToken(userToken);

        UserEntity userEntity = this.userEntityService
                .getByUsername(username);

        return this.orderDao.findByUserEntity(userEntity);
    }

    @Override
    public Order getById(long id) {
        return this.orderDao
                .findById(id)
                .orElseThrow(() -> new com.myhangars.exception.GenericException.NotFound(id));
    }


    @Override
    public Order insert(Order order, String token) {

        List<Product_Order> product_orders = order.getProduct_orders();

        this.checkIfEachProductExists(product_orders);
        this.checkIfExistsEnougthStock(product_orders);
        this.checkIfTotalPricesMatch(order);

        this.decreaseProducts_HangarQuantity(product_orders);

        return this.orderDao.save(
                this.linkUserEntityToOrder(token, order)
        );
    }

    private long getUserProfileIdByToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserProfile userProfile;

        long userEntityId = this.userEntityService.getByUsername(username).getId();
        userProfile = this.userProfileService.getByUserEntityId(userEntityId);

        return userProfile.getId();

    }

    private void checkIfEachProductExists(List<Product_Order> product_orders) {

        product_orders.forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            this.products_hangarService.getHangarProduct(hangarId, productId);

        });
    }

    private void checkIfExistsEnougthStock(List<Product_Order> product_orders) {

        product_orders.forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            int stockQuantity = this.products_hangarService
                    .getHangarProduct(hangarId, productId)
                    .getQuantity();

            int demandQuantity = product_order.getQuantity();

            boolean existsEnoughtStock = stockQuantity >= product_order.getQuantity();

            if (!existsEnoughtStock)
                throw new OrderException.NotEnoughtStock(demandQuantity, stockQuantity);
        });

    }

    private void checkIfTotalPricesMatch(Order order) {

        float totalPriceFromBackEnd = this.getTotalPrice(order.getProduct_orders());
        String strTotalPriceBackEnd = new DecimalFormat("#.##").format(totalPriceFromBackEnd);

        float totalPriceFromFrontEnd = order.getTotalPrice();
        String strTotalPriceFrontEnd = new DecimalFormat("#.##").format(totalPriceFromFrontEnd);

        if (!strTotalPriceFrontEnd.equals(strTotalPriceBackEnd))
            throw new OrderException.TotalPricesNotMatch(strTotalPriceFrontEnd, strTotalPriceBackEnd);
    }

    private float getTotalPrice(List<Product_Order> product_orders) {

       return (float) product_orders
                .stream()
                .mapToDouble((Product_Order product_order) -> {
                    long productId = product_order.getProduct_id();
                    int quantity = product_order.getQuantity();

                    float price = this.productService.getLastPrice(productId).getPrice();

                    return quantity * price;
                }).sum();


    }

    private void decreaseProducts_HangarQuantity(List<Product_Order> product_orders) {

        product_orders.forEach(product_order -> {

            long hangarId = product_order.getHangar_id();
            long productId = product_order.getProduct_id();

            int stockQuantity = this.products_hangarService
                    .getHangarProduct(hangarId, productId)
                    .getQuantity();

            try {

                this.products_hangarService.setQuantityByHangarIdAndProductId(
                        hangarId,
                        productId,
                        stockQuantity - product_order.getQuantity()
                );

            } catch (Exception exception) {
                throw new OrderException.UnableModifyQuantityProducts_Hangar(hangarId, productId);
            }

        });
    }

    private Order linkUserEntityToOrder(String userToken, Order order) {

        String username = jwtTokenUtil.getUsernameFromToken(userToken);

        long userEntityId = this.userEntityService
                .getByUsername(username)
                .getId();

        UserEntity userEntity = this.userEntityService
                .findById(userEntityId);

        order.setUserEntity(userEntity);

        return order;
    }

}
