package pl.dtit.service;

import pl.dtit.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findOneProduct(Long id);
    void addProduct(Product product);
    Product updateProduct (Product product, Long id);
}
