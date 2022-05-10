package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserDto signIn(UserDto userDto) {
        return userDto;
    }
}

