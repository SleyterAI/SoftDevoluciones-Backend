package CV.SoftDevoluciones.Return.Dto.ReturnDetail;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailRequest {

    @NotNull
    private Integer quantity;

}
