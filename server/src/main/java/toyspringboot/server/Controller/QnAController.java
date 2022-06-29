package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.QnAListDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Service.QnAService;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.Slack.SlackService;
import toyspringboot.server.UserAuthentication.UserAuthenticationConverter;

import java.util.List;

@Api(tags = {"QnA"})
@RestController
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;
    private final SlackService slackService;

    @ApiOperation(value = "QnA 생성", notes = "QnA 게시글 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @PostMapping("/QnAs/QnA")
    public ResponseEntity<QnADto> createQnA(@RequestHeader("Authorization") String userToken, @RequestBody QnADto qnADto) {
        slackService.postSlackMessage("새로운 QnA가 생성되었습니다.");
        return new ResponseEntity<>(qnAService.createQnA(userToken, qnADto), HttpStatus.OK);
    }

    @ApiOperation(value = "QnA 조회", notes = "QnA 게시글 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "접근 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다.")
    })
    @GetMapping("/QnAs/{QnAId}")
    public ResponseEntity<QnADto> readQnA(@RequestHeader("Authorization") String userToken,
                                         @PathVariable(value = "QnAId") Long qnAId) {
        return new ResponseEntity<>(qnAService.readQnA(userToken, qnAId), HttpStatus.OK);
    }

    @ApiOperation(value = "QnA 수정", notes = "QnA 게시글 수정 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "접근 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 QnA 입니다.")
    })
    @PutMapping("/QnAs/QnA")
    public QnADto updateQnA(@RequestHeader("Authorization") String userToken,
                          @RequestBody QnADto qnADto) {
        return qnAService.updateQnA(userToken, qnADto);
    }


    @ApiOperation(value = "QnA 삭제", notes = "QnA 게시글 삭제 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "삭제 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 QnA 입니다.")
    })
    @DeleteMapping("/QnAs/QnA")
    public QnADto deleteQnA(@RequestHeader("Authorization") String userToken,
                          @RequestBody QnADto qnADto) {
        return qnAService.deleteQnA(userToken, qnADto);
    }

    @ApiOperation(value = "QnA 검색", notes = "QnA 게시글 (제목, 내용) 검색 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success")
    })
    @GetMapping("/QnAs/QnA/search")
    public QnAListDto searchQnA(@RequestParam(value = "query") String query) {
        return qnAService.searchQnA(query);
    }

    @ApiOperation(value = "QnA 리스트 페이지", notes = "QnA 페이지별 리스트 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success")
    })
    @GetMapping("/QnAs/QnA/page")
    public ResponseEntity<QnAListDto>  QnAPage(@RequestParam(value = "page") Long page) {
        System.out.println("page = " + page);
        return new ResponseEntity<>(qnAService.pageQnA(page), HttpStatus.OK);
    }
}
