package CV.SoftDevoluciones.Return.Dto.Return;

import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnAdminResponse {
    private Long return_id;
    private String user_name;
    private Long order_id;

    private LocalDateTime date;

    private ReturnStatus status;
    private BigDecimal returnTotal;

    private String operatorNotes;
}
