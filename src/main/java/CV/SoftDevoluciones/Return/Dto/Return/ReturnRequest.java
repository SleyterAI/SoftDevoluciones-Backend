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

    @NotNull(message = "El amount es obligatorio")
    private BigDecimal amount; //amount generado por el total de productos
    //si se compro 3 se quiere devolver 2 -> 2*price = amount

    @NotNull(message = "El returndetail es obligatorio")
    private ReturnDetailRequest returnDetailRequest;
}
