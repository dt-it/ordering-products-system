package pl.dtit.service;

import pl.dtit.model.Order;
import pl.dtit.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    void createNewOrder();
    void addProductsToOrder(Long orderId, Long... productsId);
//    void finalizeOrder(Long orderId);
    Order finalizeOrder(Long orderId);
    List<Order> getOrdersBetween(LocalDate from, LocalDate to);
    void updateTotalPriceInOrder(Long orderId);
}
