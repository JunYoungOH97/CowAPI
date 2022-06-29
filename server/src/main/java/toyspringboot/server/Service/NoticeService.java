package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.*;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Repository.NoticeRepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.UserAuthentication.UserAuthenticationConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final UserAuthenticationConverter userAuthenticationConverter;
    public NoticeDto createNotice(String userToken, NoticeDto noticeDto) {
        String userEmail = userAuthenticationConverter.getUserEmailFromRequestHeader(userToken);
        UserDto userDto = UserDto.of(userRepository.findByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        noticeDto.setCreateNoticeDto(userDto);
        Notice notice = NoticeDto.toEntity(noticeDto);
        return NoticeDto.of(noticeRepository.save(notice));
    }

    public NoticeDto readNotice(String userToken, Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 공지 입니다."));
        return NoticeDto.of(notice);
    }

    public NoticeDto updateNotice(String userToken, NoticeDto noticeDto) {
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "존재하지 않는 공지 입니다."));;
        noticeRepository.updateNotice(notice, noticeDto);
        return NoticeDto.of(notice);
    }

    public NoticeDto deleteNotice(String userToken, Long noticeId, String updater) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "존재하지 않는 공지 입니다."));
        noticeRepository.deleteNotice(notice, updater);
        return NoticeDto.of(notice);
    }

    public NoticeListDto readNoticeList(Long pageId) {
        Long startIndex = (pageId - 1L) * 5L;
        List<Notice> noticeList = noticeRepository.findByPage(startIndex, 5L);
        Iterator<Notice> iter = noticeList.listIterator();

        List<NoticeDto> noticeDtoList = new ArrayList<>();
        while(iter.hasNext()) {
            noticeDtoList.add(NoticeDto.of(iter.next()));
        }
        return NoticeListDto.of(noticeDtoList);
    }
}
