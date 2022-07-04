package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.NoticeDto;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.ResposneDto.NoticeListResponseDto;
import server.server.Domain.ResposneDto.NoticeResponseDto;
import server.server.Service.NoticeService;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;


    @PostMapping("/notice")
    public ResponseEntity createQna(@RequestHeader("Authorization") String userToken,
                                    @RequestBody NoticeDto noticeDto) {

        noticeService.createNotice(TokenDto.builder().accessToken(userToken).build(), noticeDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<NoticeResponseDto> readQna(@RequestHeader("Authorization") String userToken,
                                                     @PathVariable(value = "noticeId") Long noticeId) {
        return ResponseEntity.ok()
                .body(noticeService.readNotice(NoticeDto.builder().id(noticeId).build()).toResponse());
    }

    @PutMapping("/notice/{noticeId}")
    public ResponseEntity updateNotice(@RequestHeader("Authorization") String userToken,
                                       @PathVariable(value = "noticeId") Long noticeId,
                                       @RequestBody NoticeDto noticeDto) {
        noticeDto.setId(noticeId);
        noticeService.updateNotice(TokenDto.builder().accessToken(userToken).build(), noticeDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity deleteQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "noticeId") Long noticeId) {


        noticeService.deleteNotice(TokenDto.builder().accessToken(userToken).build(), noticeId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/notice/list/{page}")
    public ResponseEntity<NoticeListResponseDto> QnaPage(@RequestHeader("Authorization") String userToken,
                                                         @PathVariable(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(noticeService.pageNotice(page).toResponse());
    }
}
