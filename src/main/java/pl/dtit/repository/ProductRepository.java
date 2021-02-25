package pl.dtit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dtit.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
