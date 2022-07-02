package toyspringboot.server.Controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.ResponseDto.TokenResponseDto;
import toyspringboot.server.Domain.ResponseDto.UserResponseDto;
import toyspringboot.server.Service.UserService;

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
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(userService.signUp(userDto).toResponse());
    }

    @ApiOperation(value = "로그인", notes = "기존 사용자 로그인 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/users/signin")
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(userService.signIn(userDto).toResponse());
    }

    @ApiOperation(value = "회원 수정", notes = "기존 사용자의 정보를 수정하는 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/users/user")
    public ResponseEntity<UserResponseDto> updateUser(@RequestHeader("Authorization") String userToken, @RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(userService.updateUser(userToken, userDto).toResponse());
    }

    @ApiOperation(value = "회원 삭제", notes = "기존 사용자를 삭제하는 api 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/users/user")
    public ResponseEntity<UserResponseDto> deleteUser(@RequestHeader("Authorization") String userToken, @RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(userService.deleteUser(userToken, userDto).toResponse());
    }
}
