package CV.SoftDevoluciones.User.Service.Interface;

import CV.SoftDevoluciones.User.Dto.MessageResponseDto;
import CV.SoftDevoluciones.User.Dto.Register.RegisterRequestDto;
import CV.SoftDevoluciones.User.Dto.User.UserResponseDto;
import CV.SoftDevoluciones.User.Entity.User;

import java.util.List;

public interface IUserService {
    //Create
    MessageResponseDto createUser(RegisterRequestDto registerRequestDto);

    //Read
    List<UserResponseDto> getAllUser();
    User getUserById(Long id);

    //Update
    User updateUser(Long id, RegisterRequestDto registerRequestDto);

    //Delete
    MessageResponseDto deleteUser(Long id);
}
