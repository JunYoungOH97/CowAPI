package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;
import server.server.Jwt.TokenConverter;
import server.server.Jwt.TokenProvider;


import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UsersRepository usersRepository;
    private final TokenProvider tokenProvider;
    private final TokenConverter tokenConverter;

    public UsersDto signUp(UsersDto userDto) {
        if(usersRepository.existsByEmail(userDto.getEmail())) throw new ResponseStatusException(CONFLICT, "이미 가입되어 있는 유저입니다");
        userDto.setCreatedUser();
        return signIn(UsersDto.of(usersRepository.save(UsersDto.toEntity(userDto))));
    }

    public UsersDto signIn(UsersDto userDto) {
        Users foundUser = usersRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        if(!foundUser.getPassword().equals(userDto.getPassword())) throw new ResponseStatusException(BAD_REQUEST, "비밀번호가 틀렸습니다.");

        // new user authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), "", null);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        UsersDto usersDto = UsersDto.of(foundUser);
        usersDto.setUserToken(tokenDto);
        return usersDto;
    }

    public UsersDto updateUser(TokenDto userToken, UsersDto userDto) {
        String email = tokenConverter.getEmail(userToken);
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재 하지 않는 유저입니다."));
        usersRepository.updateUser(user, userDto);
        return UsersDto.of(user);
    }

    public UsersDto deleteUser(String userToken, UsersDto userDto) {
        Users user = usersRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재 하지 않는 유저입니다."));
        usersRepository.deleteUser(user);
        return UsersDto.of(user);
    }

}
