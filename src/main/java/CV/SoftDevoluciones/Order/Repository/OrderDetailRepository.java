package CV.SoftDevoluciones.Order.Repository;

import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
