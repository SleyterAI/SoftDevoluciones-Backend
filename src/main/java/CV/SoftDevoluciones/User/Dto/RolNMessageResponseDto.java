package CV.SoftDevoluciones.User.Dto;

import CV.SoftDevoluciones.User.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolNMessageResponseDto {

    private String message;
    private UserRole role;
}
