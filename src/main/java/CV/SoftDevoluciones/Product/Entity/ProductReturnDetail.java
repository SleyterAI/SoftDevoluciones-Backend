package CV.SoftDevoluciones.Product.Entity;

import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ProductReturnDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReturnDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;


    /*private String amount;*/ //analizar

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_return_id", nullable = false)
    private ProductReturnRequest returnRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;
}
