package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Domain.Repository.UserRepository;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class QnAService {
    private final QnARepository qnARepository;
    private final UserRepository userRepository;

    public QnADto createQnA(String userToken, QnADto qnADto) {
        UserDto userDto = UserDto.of(userRepository.findByEmail(userToken).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.")));
        qnADto.setCreateQnA(userDto);
        QnA qnA = QnA.of(qnADto);
        return QnADto.of(qnARepository.save(qnA));
    }
}
