package CV.SoftDevoluciones.User.Service;

import CV.SoftDevoluciones.Security.Jwt.JwtService;
import CV.SoftDevoluciones.User.Dto.Login.LoginRequestDto;
import CV.SoftDevoluciones.User.Dto.Login.LoginResponseDto;
import CV.SoftDevoluciones.User.Service.Interface.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                ));
        String token = jwtService.generarToken(authentication);
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String roleUser = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        return new LoginResponseDto(token,userDetails.getUsername(), roleUser);
    }
}
