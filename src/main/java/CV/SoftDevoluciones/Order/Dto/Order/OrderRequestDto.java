package CV.SoftDevoluciones.Order.Dto.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderRequestDto {

    /*
    @NotNull(message = "User mandatory")
    private User user;*/

    @NotBlank
    @Pattern(regexp = "^[0-9]{9}$",
            message = "Phone number must have 9 digits")
    private String phoneNumber;

    @NotBlank
    private String address;

    /*
    @NotEmpty(message = "Order detail request dto is missing")
    private List<@Valid OrderDetailRequestDto> orderDetailRequestDto = new ArrayList<>();*/
}
