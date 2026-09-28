package CV.SoftDevoluciones.User.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginRequestDto;
import com.ConsigueVentas.TiendaAccesorios.User.Dto.Login.LoginResponseDto;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
