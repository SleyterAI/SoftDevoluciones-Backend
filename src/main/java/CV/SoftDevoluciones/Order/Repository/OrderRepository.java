package CV.SoftDevoluciones.Order.Repository;

import CV.SoftDevoluciones.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmail(String email);
}
