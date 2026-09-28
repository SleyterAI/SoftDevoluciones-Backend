package CV.SoftDevoluciones.Product.Entity;

import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ProductReturnDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReturnDetail {
    private Long id;
    private LocalDateTime quantity;
    private String amount;
    private ProductReturnRequest returnRequest;
    private OrderDetail orderDetail;
}
