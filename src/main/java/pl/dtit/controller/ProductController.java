package pl.dtit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dtit.model.Product;
import pl.dtit.service.impl.ProductServiceImpl;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductServiceImpl productServiceImpl;


    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "name") String orderBy) {
        List<Product> products = productServiceImpl.findAll();
        if ("name".equals(orderBy)) {
            products.sort(Comparator.comparing(Product::getName));
        } else if ("price".equals(orderBy)) {
            products.sort(Comparator.comparing(Product::getPrice));
        } else if ("id".equals(orderBy)) {
            products.sort(Comparator.comparing(Product::getId));
        }
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable Long id) {
        if (id > productServiceImpl.findAll().size() || id < 0) {
            return ResponseEntity.notFound().build();
        } else {
            Product foundProduct = productServiceImpl.findOneProduct(id);
            return ResponseEntity.ok(foundProduct);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProduct(@RequestBody Product product) {
            productServiceImpl.addProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@RequestBody Product product, @PathVariable Long id) {
        if (id > productServiceImpl.findAll().size() || id < 0) {
            return ResponseEntity.notFound().build();
        } else {
            Product updatedProduct = productServiceImpl.updateProduct(product, id);
            return ResponseEntity.accepted().build();
        }
    }


}
