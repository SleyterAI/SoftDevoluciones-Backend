package CV.SoftDevoluciones.User.Service.Interface;


import CV.SoftDevoluciones.User.Dto.Login.LoginRequest;
import CV.SoftDevoluciones.User.Dto.Login.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequestDto);
}
