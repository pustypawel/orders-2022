package pl.edu.wszib.order.infrastructure.persistance.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ProductEntity, String> {
}
