package server.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"Notice"})
public class NoticeController {
    private final NoticeService noticeService;
    
    @ApiOperation(value = "공지 생성", notes = "공지를 생성하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/notice")
    public ResponseEntity createQna(@RequestHeader("Authorization") String userToken,
                                    @RequestBody NoticeDto noticeDto) {

        noticeService.createNotice(TokenDto.builder().accessToken(userToken).build(), noticeDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "공지 조회", notes = "공지를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<NoticeResponseDto> readQna(@RequestHeader("Authorization") String userToken,
                                                     @PathVariable(value = "noticeId") Long noticeId) {
        return ResponseEntity.ok()
                .body(noticeService.readNotice(NoticeDto.builder().id(noticeId).build()).toResponse());
    }

    @ApiOperation(value = "공지 수정", notes = "공지를 수정하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/notice/{noticeId}")
    public ResponseEntity updateNotice(@RequestHeader("Authorization") String userToken,
                                       @PathVariable(value = "noticeId") Long noticeId,
                                       @RequestBody NoticeDto noticeDto) {
        noticeDto.setId(noticeId);
        noticeService.updateNotice(TokenDto.builder().accessToken(userToken).build(), noticeDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "공지 삭제", notes = "공지를 삭제하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity deleteQna(@RequestHeader("Authorization") String userToken,
                                    @PathVariable(value = "noticeId") Long noticeId) {


        noticeService.deleteNotice(TokenDto.builder().accessToken(userToken).build(), noticeId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "공지 list", notes = "공지 list를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/notice/list/{page}")
    public ResponseEntity<NoticeListResponseDto> QnaPage(@RequestHeader("Authorization") String userToken,
                                                         @PathVariable(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(noticeService.pageNotice(page).toResponse());
    }
}
