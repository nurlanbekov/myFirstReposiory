package kg.easy.sellservice.controllers;

import kg.easy.sellservice.models.dtos.UserDto;
import kg.easy.sellservice.services.UserService;
import kg.easy.sellservice.models.dtos.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(String login){
        return userService.sendCode(login);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody LoginDto login){
        return userService.getToken(login);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyLogin(@RequestHeader String token){
        return userService.verifyLogin(token);
    }
}
