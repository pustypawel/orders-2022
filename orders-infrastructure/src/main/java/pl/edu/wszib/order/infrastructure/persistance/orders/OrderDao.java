package pl.edu.wszib.order.infrastructure.persistance.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity, String> {
}
