package server.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.OAuthUserToken;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.ResposneDto.OAuthUserResponseDto;
import server.server.Domain.ResposneDto.UserLoginResponseDto;
import server.server.Domain.ResposneDto.UserResponseDto;
//import server.server.Service.OAuthUserService;
import server.server.Service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Api(tags = {"User"})
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/signup")
    public ResponseEntity<UserLoginResponseDto> signUp(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signUp(userDto).toLoginResponse());
    }
    
    @ApiOperation(value = "로그인", notes = "로그인 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponseDto> signIn(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signIn(userDto).toLoginResponse());
    }

    @ApiOperation(value = "마이페이지", notes = "마이페이지 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/mypage")
    public ResponseEntity<UserResponseDto> mypage(@RequestHeader(value = "Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(userService.readMypage(TokenDto.builder().accessToken(userToken).build()).toResponse());
    }

    @ApiOperation(value = "재발급", notes = "secret key 재발급 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/reissuance")
    public ResponseEntity<UserResponseDto> reissuance(@RequestHeader(value = "Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(userService.reissuance(TokenDto.builder().accessToken(userToken).build()).toResponse());
    }


    @ApiOperation(value = "회원수정", notes = "회원수정 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestHeader(value = "Authorization") String userToken,
                                                      @RequestBody UsersDto usersDto) {

        userService.updateUser(TokenDto.builder().accessToken(userToken).build(), usersDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "회원삭제", notes = "회원삭제 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestHeader(value = "Authorization") String userToken) {
        userService.deleteUser(TokenDto.builder().accessToken(userToken).build());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "OAuth Naver", notes = "OAuth Naver API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/login/oauth/naver")
    public OAuthUserResponseDto OAuthLogin(@RequestHeader(value = "Authorization") String userToken) {
        return userService.OAuthUser(OAuthUserToken.builder().userToken(userToken).build());
    }
}
