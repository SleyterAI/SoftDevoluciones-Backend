package CV.SoftDevoluciones.Return.Entity;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ProductReturnRequests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @Column(nullable = false, length = 30)
    private String reason;

    @Column(nullable = false, length = 30)
    private String comment;

    @Column(length = 50)
    private String operatorNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "return_detail")
    private ReturnDetail returnDetail;
}
