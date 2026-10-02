package CV.SoftDevoluciones.Order.Repository;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
   List<Order> findByUserEmailAndStatus(String email, OrderStatus status);
   List<Order> findByStatus(OrderStatus status);
    /*Optional<Order> findByUserEmail(String email);*/
}
