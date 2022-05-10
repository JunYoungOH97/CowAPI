package toyspringboot.server.Controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;

@Api(tags = {"사용자"})
@RequestMapping(value = "/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "신규 사용자 회원가입 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 409, message = "이미 가입되어 있는 이메일입니다."),
    })
    @PostMapping("/users/newUser")
    public UserDto signUp(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }
}
