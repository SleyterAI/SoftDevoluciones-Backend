package CV.SoftDevoluciones.Return.Dto;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.User.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    //blank por el momento operador hace update en su gestion
    private String operatorNotes;


}
