package toyspringboot.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Service.NoticeService;

@Api(tags = {"공지"})
@RequestMapping(value = "/api/v1")
@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = "공지 생성", notes = "공지사항 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "공지를 생성할 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @PostMapping("/admin/notices/notice")
    public NoticeDto createNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return noticeService.createNotice(userToken, noticeDto);
    }

    @ApiOperation(value = "공지 조회", notes = "공지사항 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "존재하지 않는 사용자 입니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 공지 입니다."),
    })
    @GetMapping("/user/notices/notice")
    public NoticeDto readNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return noticeService.readNotice(userToken, noticeId);
    }

    @ApiOperation(value = "공지 수정", notes = "공지사항 수정 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "존재하지 않는 공지 입니다."),
            @ApiResponse(code = 403, message = "공지를 수정할 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @PutMapping("/admin/notices/notice")
    public NoticeDto updateNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(userToken, noticeDto);
    }

    @ApiOperation(value = "공지 삭제", notes = "공지사항 삭제 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "존재하지 않는 공지 입니다."),
            @ApiResponse(code = 403, message = "공지를 수정할 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @DeleteMapping("/admin/notices/notice")
    public NoticeDto deleteNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return noticeService.deleteNotice(userToken, noticeId, "API");
    }
}
