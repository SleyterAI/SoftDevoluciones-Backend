package CV.SoftDevoluciones.Return.Entity;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "returns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Return {

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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    //---------------------------------------------------------------

    @OneToOne(mappedBy = "aReturn")
    private ReturnDetail returnDetail;
}
