package kg.easy.sellservice.services;

import kg.easy.sellservice.models.dtos.UserDto;
import kg.easy.sellservice.models.dtos.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(UserDto userDto);

    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> getToken(LoginDto login);

    ResponseEntity<?> verifyLogin(String token);
}
