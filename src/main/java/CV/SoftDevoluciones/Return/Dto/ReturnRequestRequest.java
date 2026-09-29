package CV.SoftDevoluciones.Return.Dto;

import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnRequestRequest {

    @NotBlank
    private String reason;

    @NotBlank
    private String comment;

    @NotNull
    private Integer quantity;

    @NotNull
    private ReturnDetail returnDetail;
}
