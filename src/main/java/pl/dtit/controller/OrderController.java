package pl.dtit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dtit.model.Order;
import pl.dtit.service.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping
    public List<Order> getAllOrders(@RequestParam(defaultValue = "id") String orderBy) {
        return orderServiceImpl.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder() {
        orderServiceImpl.createNewOrder();
    }

    @PostMapping("/{orderId}/products")
    public void addProductsToOrder(@PathVariable(value = "orderId") Long orderId,
                                   @RequestParam(value = "productId") Long... productsId) {
        orderServiceImpl.addProductsToOrder(orderId, productsId);

    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> finalizeOrder(@PathVariable(value = "orderId") Long orderId) {
        if (orderId > orderServiceImpl.findAll().size() || orderId < 0) {
            return ResponseEntity.notFound().build();
        } else {
            Order order = orderServiceImpl.finalizeOrder(orderId);
            return ResponseEntity.accepted().build();
        }
    }

    @PutMapping("/{orderId}/products")
    void refreshOrder(@PathVariable(value = "orderId") Long orderId) {
        orderServiceImpl.updateTotalPriceInOrder(orderId);
    }

    @GetMapping("/{from}/{to}")
    public List<Order> getOrdersBetween(@PathVariable(value = "from")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                        @PathVariable(value = "to")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                LocalDate to) {
        return orderServiceImpl.getOrdersBetween(from, to);
    }
}
