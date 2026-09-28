package CV.SoftDevoluciones.User.Controller;

import CV.SoftDevoluciones.User.Dto.MessageResponseDto;
import CV.SoftDevoluciones.User.Dto.Register.RegisterRequestDto;
import CV.SoftDevoluciones.User.Dto.RolNMessageResponseDto;
import CV.SoftDevoluciones.User.Dto.User.UserResponseDto;
import CV.SoftDevoluciones.User.Entity.User;
import CV.SoftDevoluciones.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDto> createUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(registerRequestDto));
    }

    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PatchMapping("/{id}/role")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<RolNMessageResponseDto> updateRole(
            @PathVariable Long id, @RequestBody User newRole) {

        return ResponseEntity.ok(userService.updateRole(id, newRole.getRole()));
    }

    @DeleteMapping("/{id}")
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<MessageResponseDto> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
