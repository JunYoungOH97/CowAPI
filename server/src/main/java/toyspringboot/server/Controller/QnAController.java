package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Service.QnAService;
import toyspringboot.server.Service.UserService;

@Api(tags = {"QnA 게시판"})
@RequestMapping(value = "/api/v1")
@RestController
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    @ApiOperation(value = "QnA 생성", notes = "QnA 게시글 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @PostMapping("/QnAs/QnA")
    public QnADto createQnA(@RequestHeader("Authorization") String userToken, @RequestBody QnADto qnADto) {
        return qnAService.createQnA(userToken, qnADto);
    }


    @ApiOperation(value = "QnA 조회", notes = "QnA 게시글 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "접근 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다.")
    })
    @GetMapping("/QnAs/{QnAId}")
    public QnADto readQnA(@RequestHeader("Authorization") String userToken,
                          @PathVariable(value = "QnAId") Long qnAId) {
        System.out.println("userToken = " + userToken + ", qnAId = " + qnAId.toString());
        return qnAService.readQnA(userToken, qnAId);
    }
}
