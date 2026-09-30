package CV.SoftDevoluciones.Return.Entity;

import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "return_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    @OneToOne(mappedBy = "returnDetail")
    private Return aReturn;
}
