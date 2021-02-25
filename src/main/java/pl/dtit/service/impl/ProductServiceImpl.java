package pl.dtit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dtit.exceptions.ProductNotFoundException;
import pl.dtit.model.Product;
import pl.dtit.repository.ProductRepository;
import pl.dtit.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOneProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void addProduct(Product product) {
            productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product productFromDb = productRepository.findById(id).get();
        productFromDb.setName(product.getName());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }

}
