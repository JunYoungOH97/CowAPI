package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Repository.NoticeRepository;
import toyspringboot.server.Domain.Repository.UserRepository;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    public NoticeDto createNotice(String userToken, NoticeDto noticeDto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        if(!userDto.getAdmin()) throw new ResponseStatusException(FORBIDDEN, "공지 생성 권한이 없습니다.");
        noticeDto.setCreateNoticeDto(userDto);
        Notice notice = NoticeDto.toEntity(noticeDto);
        return NoticeDto.of(noticeRepository.save(notice));
    }

    public NoticeDto readNotice(String userToken, Long noticeId) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "존재하지 않는 사용자 입니다.")));
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 공지 입니다."));
        return NoticeDto.of(notice);
    }

    public NoticeDto updateNotice(String userToken, NoticeDto noticeDto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        if(!userDto.getAdmin()) throw new ResponseStatusException(FORBIDDEN, "공지 수정 권한이 없습니다.");
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "존재하지 않는 공지 입니다."));;
        noticeRepository.updateNotice(notice, noticeDto);
        return NoticeDto.of(notice);
    }

    public NoticeDto deleteNotice(String userToken, Long noticeId, String updater) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        if(!userDto.getAdmin()) throw new ResponseStatusException(FORBIDDEN, "공지 삭제 권한이 없습니다.");
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "존재하지 않는 공지 입니다."));;
        noticeRepository.deleteNotice(notice, updater);
        return NoticeDto.of(notice);
    }
}
