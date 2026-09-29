package CV.SoftDevoluciones.Security.Service;


import CV.SoftDevoluciones.User.Entity.User;
import CV.SoftDevoluciones.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "+ email));

        // Convertimos nuestro Usuario a un objeto que Spring Security entiende (UserDetails)
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getUserRole()))
                .build();

    }

     //metodo que permite obviar loadUserByUsername solo si
    //la clase entity user hace implements userdetails
    //con sus metodos y atributos correspondientes
    /*@Bean
    public UserDetailsService userDetailsService(){
        return username -> usuarioRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }*/
}
