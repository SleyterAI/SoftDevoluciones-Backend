package CV.SoftDevoluciones.User.Dto.Card;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CardResponseDto {

    private String cardNumber;
    private LocalDate expirationDate;
}
