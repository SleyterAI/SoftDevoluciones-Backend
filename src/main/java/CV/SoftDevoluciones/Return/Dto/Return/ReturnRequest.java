package CV.SoftDevoluciones.Return.Dto.Return;

import CV.SoftDevoluciones.Return.Dto.ReturnDetail.ReturnDetailRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRequest {

    @NotBlank(message = "El motivo es obligatorio")
    private String reason;

    @NotBlank(message = "El comentario es obligatorio")
    private String comment;

    @NotNull(message = "Quantity es obligatorio")
    private Integer quantity;

    @NotNull(message = "El returndetail es obligatorio")
    private Long orderDetail_id;
}
