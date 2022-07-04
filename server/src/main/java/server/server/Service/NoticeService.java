package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.*;
import server.server.Domain.Entity.Notice;
import server.server.Domain.Entity.Qna;
import server.server.Domain.Repository.NoticeRepository;
import server.server.Jwt.TokenConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final TokenConverter tokenConverter;

    public void createNotice(TokenDto userToken, NoticeDto noticeDto) {
        UsersDto userDto = UsersDto.of(tokenConverter.getUser(userToken));

        noticeDto.setCreateNotice(userDto);
        Notice notice = NoticeDto.toEntity(noticeDto);
        noticeRepository.save(notice);
    }

    public NoticeDto readNotice(NoticeDto noticeDto) {
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 공지 입니다."));
        return NoticeDto.of(notice);
    }

    public void updateNotice(TokenDto userToken, NoticeDto noticeDto) {
        UsersDto userDto = UsersDto.of(tokenConverter.getUser(userToken));
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 공지 입니다."));

        if (notice.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 공지 입니다.");
        if (!userDto.getEmail().equals(notice.getUser().getEmail()))
            throw new ResponseStatusException(FORBIDDEN, "접근권한이 없습니다.");

        noticeRepository.updateNotice(notice, noticeDto);
    }

    public void deleteNotice(TokenDto userToken, Long noticeId) {
        UsersDto usersDto = UsersDto.of(tokenConverter.getUser(userToken));

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다."));
        if(notice.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다.");
        if(!notice.getUser().getEmail().equals(usersDto.getEmail())) throw new ResponseStatusException(FORBIDDEN, "접근권한이 없습니다.");

        noticeRepository.deleteNotice(notice);
    }


    public NoticeListDto pageNotice(Long pageId) {
        Long startQnaIndex = (pageId - 1L) * 5L;
        List<Notice> noticeList = noticeRepository.findByPage(startQnaIndex, 5L);
        Iterator<Notice> iter = noticeList.listIterator();

        List<NoticeDto> noticeDtoList = new ArrayList<>();
        while(iter.hasNext()) {
            noticeDtoList.add(NoticeDto.of(iter.next()));
        }

        return NoticeListDto.builder()
                .lastPage((long) noticeDtoList.size())
                .noticeDtoList(noticeDtoList)
                .build();
    }
}
