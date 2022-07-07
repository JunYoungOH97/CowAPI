package server.server.Controller;

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
public class QnaController {
    private final SlackService slackService;
    private final QnaService qnaService;

    @PostMapping("/qna")
    public ResponseEntity createQna(@RequestHeader("Authorization") String userToken,
                                    @RequestBody QnaDto qnaDto) {

        slackService.postSlackMessage("새로운 Qna가 생성되었습니다.");

        qnaService.createQna(TokenDto.builder().accessToken(userToken).build(), qnaDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<QnaResponseDto> readQna(@RequestHeader("Authorization") String userToken,
                                                  @PathVariable(value = "qnaId") Long qnAId) {
        return ResponseEntity.ok()
                .body(qnaService.readQna(qnAId).toResponse());
    }

    @PutMapping("/qna/{qnaId}")
    public ResponseEntity updateQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "qnaId") Long qnAId,
                                    @RequestBody QnaDto qnaDto) {
        qnaDto.setId(qnAId);
        qnaService.updateQna(TokenDto.builder().accessToken(userToken).build(), qnaDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/qna/{qnaId}")
    public ResponseEntity deleteQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "qnaId") Long qnAId) {


        qnaService.deleteQna(TokenDto.builder().accessToken(userToken).build(), qnAId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/qna/list/{page}")
    public ResponseEntity<QnaListResponseDto> QnaPage(@RequestHeader("Authorization") String userToken,
                                                      @PathVariable(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(qnaService.pageQna(page).toResponse());
    }
}
