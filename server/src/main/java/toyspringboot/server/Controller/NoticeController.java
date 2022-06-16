package toyspringboot.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Dto.NoticeListDto;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Service.NoticeService;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/admin/notices/notice")
    public NoticeDto createNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return noticeService.createNotice(userToken, noticeDto);
    }

    @GetMapping("/user/notices/notice")
    public NoticeDto readNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return noticeService.readNotice(userToken, noticeId);
    }

    @PutMapping("/admin/notices/notice")
    public NoticeDto updateNotice(@RequestHeader("Authorization") String userToken, @RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(userToken, noticeDto);
    }

    @DeleteMapping("/admin/notices/notice")
    public NoticeDto deleteNotice(@RequestHeader("Authorization") String userToken, @RequestParam("noticeId") Long noticeId) {
        return noticeService.deleteNotice(userToken, noticeId, "API");
    }

    @GetMapping("/user/notices/notice/page")
    public ResponseEntity<NoticeListDto> readNoticeList(@RequestParam(value = "page") Long page) {
        return new ResponseEntity<>(noticeService.readNoticeList(page), HttpStatus.OK);
    }
}
