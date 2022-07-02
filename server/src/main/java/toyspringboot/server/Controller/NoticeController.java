package toyspringboot.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.ResponseDto.NoticeListResponseDto;
import toyspringboot.server.Domain.ResponseDto.NoticeResponseDto;
import toyspringboot.server.Service.NoticeService;

@Api(tags = {"Notice"})
@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = "공지 생성", notes = "공지사항 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/admin/notices/notice")
    public ResponseEntity<NoticeResponseDto> createNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return ResponseEntity.ok()
                .body(noticeService.createNotice(userToken, noticeDto).toResponse());
    }

    @ApiOperation(value = "공지 조회", notes = "공지사항 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/user/notices/notice")
    public ResponseEntity<NoticeResponseDto> readNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return ResponseEntity.ok()
                .body(noticeService.readNotice(userToken, noticeId).toResponse());
    }

    @ApiOperation(value = "공지 수정", notes = "공지사항 수정 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PutMapping("/admin/notices/notice")
    public ResponseEntity<NoticeResponseDto> updateNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return ResponseEntity.ok()
                .body(noticeService.updateNotice(userToken, noticeDto).toResponse());
    }

    @ApiOperation(value = "공지 삭제", notes = "공지사항 삭제 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @DeleteMapping("/admin/notices/notice")
    public ResponseEntity<NoticeResponseDto> deleteNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return ResponseEntity.ok()
                .body(noticeService.deleteNotice(userToken, noticeId, "API").toResponse());
    }

    @ApiOperation(value = "공지 페이지네이션", notes = "공지사항 페이지네이션 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/user/notices/notice/page")
    public ResponseEntity<NoticeListResponseDto> readNoticeList(@RequestParam(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(noticeService.readNoticeList(page).toResponse());
    }
}
