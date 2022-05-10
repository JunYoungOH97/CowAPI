package toyspringboot.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/newUser")
    public UserDto signUp(@RequestBody UserDto userDto) {
        return userDto;
    }
}
