package CV.SoftDevoluciones.Order.Dto.OrderDetail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetForProduct {
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
}
