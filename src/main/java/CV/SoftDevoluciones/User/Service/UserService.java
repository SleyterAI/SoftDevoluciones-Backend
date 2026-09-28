package CV.SoftDevoluciones.User.Service;

import CV.SoftDevoluciones.GlobalException.DuplicateResourceException;
import CV.SoftDevoluciones.User.Dto.MessageResponseDto;
import CV.SoftDevoluciones.User.Dto.Register.RegisterRequestDto;
import CV.SoftDevoluciones.User.Dto.RolNMessageResponseDto;
import CV.SoftDevoluciones.User.Dto.User.UserResponseDto;
import CV.SoftDevoluciones.User.Entity.User;
import CV.SoftDevoluciones.User.Enum.UserRole;
import CV.SoftDevoluciones.User.Repository.UserRepository;
import CV.SoftDevoluciones.User.Service.Interface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MessageResponseDto createUser(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(registerRequestDto.getPassword())
        );
        user.setUserRole(UserRole.CLIENTE);

        userRepository.save(user);

        return new MessageResponseDto("User created correctly");
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getUserRole()
                ))
                .toList();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(Long id, RegisterRequestDto registerRequestDto) {
        return null;
    }

    @Override
    public MessageResponseDto deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User doesnt exist");
        }
        userRepository.deleteById(id);
        return new MessageResponseDto("User deleted correctly");
    }

    public RolNMessageResponseDto updateRole(Long id, UserRole role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User doesnt exist"));

        user.setUserRole(role);
        User promotedUser = userRepository.save(user);
        return new RolNMessageResponseDto("User promoted correctly: ", promotedUser.getUserRole());
    }
}
