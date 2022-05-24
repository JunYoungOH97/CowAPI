package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserDto signUp(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResponseStatusException(CONFLICT, "이미 가입되어 있는 유저입니다");
        }
        userDto.setCreatedUser();
        User user = UserDto.toEntity(userDto);
        return UserDto.of(userRepository.save(user));
    }

    public UserDto signIn(UserDto userDto) {
        User foundUser = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        if(!foundUser.getPassword().equals(userDto.getPassword())) throw new ResponseStatusException(BAD_REQUEST, "비밀번호가 틀렸습니다.");
        return UserDto.of(foundUser);
    }

    public UserDto updateUser(String userToken, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재 하지 않는 유저입니다."));
        userRepository.updateUser(user, userDto);
        return UserDto.of(user);
    }

    public UserDto deleteUser(String userToken, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재 하지 않는 유저입니다."));
        userRepository.deleteUser(user);
        return UserDto.of(user);
    }
}

