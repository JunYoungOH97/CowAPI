package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.QnAListDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Domain.Repository.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class QnAService {
    private final QnARepository qnARepository;
    private final UserRepository userRepository;

    public QnADto createQnA(String userToken, QnADto qnADto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        qnADto.setCreateQnA(userDto);
        QnA qnA = QnADto.toEntity(qnADto);
        return QnADto.of(qnARepository.save(qnA));
    }

    public QnADto readQnA(String userToken, Long qnAId) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "접근 권한이 없습니다.")));
        QnA qnA = qnARepository.findById(qnAId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다."));
        return QnADto.of(qnA);
    }

    public QnADto updateQnA(String userToken, QnADto qnADto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "접근 권한이 없습니다.")));
        QnA qnA = qnARepository.findById(qnADto.getId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다."));
        if(qnA.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다.");
        qnA.updateQnA(qnADto);
        return QnADto.of(qnA);
    }

    public QnADto deleteQnA(String userToken, QnADto qnADto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(FORBIDDEN, "삭제 권한이 없습니다.")));
        QnA qnA = qnARepository.findById(qnADto.getId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다."));
        if(qnA.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다.");
        qnA.deleteQnA();
        return QnADto.of(qnA);
    }

    public QnAListDto searchQnA(String query) {
        List<QnA> qnAList = qnARepository.searchByQuery(query, 5L);
        Iterator<QnA> iter = qnAList.listIterator();

        List<QnADto> qnADtoList = new ArrayList<>();
        while(iter.hasNext()) {
            qnADtoList.add(QnADto.of(iter.next()));
        }
        return QnAListDto.of(qnADtoList);
    }

    public QnAListDto pageQnA(Long pageId) {
        Long startQnAIndex = (pageId - 1L) * 5L;
        List<QnA> qnAList = qnARepository.findByPage(startQnAIndex, 5L);
        Iterator<QnA> iter = qnAList.listIterator();

        List<QnADto> qnADtoList = new ArrayList<>();
        while(iter.hasNext()) {
            qnADtoList.add(QnADto.of(iter.next()));
        }
        return QnAListDto.of(qnADtoList);
    }
}
