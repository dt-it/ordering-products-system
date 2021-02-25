package pl.dtit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dtit.exceptions.OrderNotFoundException;
import pl.dtit.exceptions.ProductNotFoundException;
import pl.dtit.model.Order;
import pl.dtit.model.Product;
import pl.dtit.model.Status;
import pl.dtit.repository.OrderRepository;
import pl.dtit.repository.ProductRepository;
import pl.dtit.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void createNewOrder() {
        Order order = new Order();
        order.setDateAdded(LocalDate.now());
        order.setStatus(Status.NEW);
        orderRepository.save(order);
    }


    @Override
    public void addProductsToOrder(Long orderId, Long... productsId) {
        //find order by id
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        //find product we want to add by id
        for (Long productId : productsId) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            //add product to order
            order.getProducts().add(product);
        }
        //update order
        updateOrder(order, orderId);
    }

    private void updateOrder(Order order, Long orderId) {
        orderRepository.findById(orderId)
                .map(orderFromDb -> {
//                    orderFromDb.setDateAdded(LocalDate.now());
                    orderFromDb.setProducts(order.getProducts());
//                    orderFromDb.setStatus(Status.NEW);
                    orderFromDb.setTotalPrice(sumProductPrices(order.getProducts()));
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private double sumProductPrices(List<Product> products) {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public Order finalizeOrder(Long orderId) {
        //find order by id
        Order orderFromDb = orderRepository.findById(orderId).get();
        //update order
        orderFromDb.setDateAdded(LocalDate.now());
        orderFromDb.setProducts(orderFromDb.getProducts());
        orderFromDb.setStatus(Status.FINALIZED);
        return orderRepository.save(orderFromDb);
    }

    @Override
    public void updateTotalPriceInOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        //update order
        if (order.getStatus().equals(Status.NEW)){
            updateOrder(order, orderId);
        }
    }

    @Override
    public List<Order> getOrdersBetween(LocalDate from, LocalDate to) {
        List<Order> ordersByDate =
                orderRepository.findAllByDateAddedBetween(from, to)
                        .stream().filter(order -> order.getStatus().equals(Status.FINALIZED))
                        .collect(Collectors.toList());
        return ordersByDate;
    }


}
