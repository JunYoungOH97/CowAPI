package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.QnaDto;
import server.server.Domain.Dto.QnaListDto;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Qna;
import server.server.Domain.Repository.QnaRepository;
import server.server.Jwt.TokenConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class QnaService {
    private final TokenConverter tokenConverter;
    private final QnaRepository qnaRepository;

    public void createQna(TokenDto userToken, QnaDto qnaDto) {
        UsersDto userDto = UsersDto.of(tokenConverter.getUser(userToken));

        qnaDto.setCreateQna(userDto);
        Qna qna = QnaDto.toEntity(qnaDto);
        QnaDto.of(qnaRepository.save(qna));
    }

    public QnaDto readQna(Long qnaId) {
        Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다."));
        return QnaDto.of(qna);
    }

    public void updateQna(TokenDto userToken, QnaDto qnaDto) {
        UsersDto userDto = UsersDto.of(tokenConverter.getUser(userToken));
        Qna qna = qnaRepository.findById(qnaDto.getId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다."));

        if(qna.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다.");
        if(!userDto.getEmail().equals(qna.getUser().getEmail())) throw new ResponseStatusException(FORBIDDEN, "접근권한이 없습니다.");

        qnaRepository.updateQna(qna, qnaDto);
    }

    public void deleteQna(TokenDto userToken, Long qnaId) {
        UsersDto usersDto = UsersDto.of(tokenConverter.getUser(userToken));

        Qna qna = qnaRepository.findById(qnaId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다."));
        if(qna.getIsDeleted().equals(true)) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 Qna 입니다.");
        if(!qna.getUser().getEmail().equals(usersDto.getEmail())) throw new ResponseStatusException(FORBIDDEN, "접근권한이 없습니다.");

        qnaRepository.deleteQna(qna);
    }

    public QnaListDto pageQna(Long pageId) {
        Long startQnaIndex = (pageId - 1L) * 5L;
        List<Qna> qnAList = qnaRepository.findByPage(startQnaIndex, 5L);
        Iterator<Qna> iter = qnAList.listIterator();

        List<QnaDto> qnADtoList = new ArrayList<>();
        while(iter.hasNext()) {
            qnADtoList.add(QnaDto.of(iter.next()));
        }

        Long lastPage = qnaRepository.countById();

        return QnaListDto.builder()
                .lastPage(lastPage)
                .qnADtoList(qnADtoList)
                .build();
    }
}
