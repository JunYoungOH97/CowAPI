package toyspringboot.server.Controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"회원"})
@RequestMapping(value = "api/v1/member")
public class HelloController {

    // 메서드 정보
    @ApiOperation(value = "특정 회원 검색", notes = "회원 번호를 기반으로 특정 회원의 정보를 검색합니다.")
    // request 정보
    @ApiImplicitParam(name = "memberId", value = "회원번호")
    // response 정보
    @ApiResponses({
            @ApiResponse(code = 200, message = "success", response = Long.class),
            @ApiResponse(code = 204, message = "member not exists"),
    })
    @GetMapping("/{memberId}")
    public Long searchUser(@PathVariable final Long memberId) {
        return memberId;
    }
}
