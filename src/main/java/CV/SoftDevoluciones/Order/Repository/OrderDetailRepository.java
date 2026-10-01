package CV.SoftDevoluciones.Order.Repository;

import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByOrderIdAndProductId(Long orderId, Long productId);
}
