package server.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.QnaDto;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.ResposneDto.QnaListResponseDto;
import server.server.Domain.ResposneDto.QnaResponseDto;
import server.server.Service.QnaService;
import server.server.Service.SlackService;

@RestController
@RequiredArgsConstructor
@Api(tags = {"QnA"})
public class QnaController {
    private final SlackService slackService;
    private final QnaService qnaService;

    @ApiOperation(value = "QnA 생성", notes = "QnA를 생성하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/qna")
    public ResponseEntity createQna(@RequestHeader("Authorization") String userToken,
                                    @RequestBody QnaDto qnaDto) {

        slackService.postSlackMessage("새로운 Qna가 생성되었습니다.");

        qnaService.createQna(TokenDto.builder().accessToken(userToken).build(), qnaDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "QnA 조회", notes = "QnA를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<QnaResponseDto> readQna(@RequestHeader("Authorization") String userToken,
                                                  @PathVariable(value = "qnaId") Long qnAId) {
        return ResponseEntity.ok()
                .body(qnaService.readQna(qnAId).toResponse());
    }


    @ApiOperation(value = "QnA 수정", notes = "QnA를 수정하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/qna/{qnaId}")
    public ResponseEntity updateQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "qnaId") Long qnAId,
                                    @RequestBody QnaDto qnaDto) {
        qnaDto.setId(qnAId);
        qnaService.updateQna(TokenDto.builder().accessToken(userToken).build(), qnaDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "QnA 삭제", notes = "QnA를 삭제하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/qna/{qnaId}")
    public ResponseEntity deleteQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "qnaId") Long qnAId) {


        qnaService.deleteQna(TokenDto.builder().accessToken(userToken).build(), qnAId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @ApiOperation(value = "QnA list", notes = "QnA list를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/qna/list/{page}")
    public ResponseEntity<QnaListResponseDto> QnaPage(@RequestHeader("Authorization") String userToken,
                                                      @PathVariable(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(qnaService.pageQna(page).toResponse());
    }
}
