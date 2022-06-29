package toyspringboot.server.Controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.TokenDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "신규 사용자 회원가입 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/users/signup")
    public UserDto signUp(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    @ApiOperation(value = "로그인", notes = "기존 사용자 로그인 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })

    @PostMapping("/users/login")
    public TokenDto signIn(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    @ApiOperation(value = "회원 수정", notes = "기존 사용자의 정보를 수정하는 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/users/user")
    public UserDto updateUser(@RequestHeader("Authorization") String userToken, @RequestBody UserDto userDto) {
        return userService.updateUser(userToken, userDto);
    }

    @ApiOperation(value = "회원 삭제", notes = "기존 사용자를 삭제하는 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/users/user")
    public UserDto deleteUser(@RequestHeader("Authorization") String userToken, @RequestBody UserDto userDto) {
        return userService.deleteUser(userToken, userDto);
    }
}
