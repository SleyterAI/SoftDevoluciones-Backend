package CV.SoftDevoluciones.User.Service.Interface;


import CV.SoftDevoluciones.User.Dto.Login.LoginRequestDto;
import CV.SoftDevoluciones.User.Dto.Login.LoginResponseDto;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
