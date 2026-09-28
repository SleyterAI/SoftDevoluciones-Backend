package CV.SoftDevoluciones.User.Service;

import CV.SoftDevoluciones.Security.Jwt.JwtService;
import CV.SoftDevoluciones.User.Dto.Login.LoginRequest;
import CV.SoftDevoluciones.User.Dto.Login.LoginResponse;
import CV.SoftDevoluciones.User.Service.Interface.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest LoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        LoginRequest.getEmail(),
                        LoginRequest.getPassword()
                ));
        String token = jwtService.generarToken(authentication);
        return new LoginResponse(token);
    }
}
