package server.server.Service;

import antlr.Token;
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


import java.math.BigInteger;
import java.security.SecureRandom;

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
        if(foundUser.getIsDeleted()) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.");

        // new user authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), "", null);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        UsersDto usersDto = UsersDto.of(foundUser);
        usersDto.setUserToken(tokenDto);
        return usersDto;
    }

    public UsersDto readMypage(TokenDto userToken) {
        Users user  = tokenConverter.getUser(userToken);
        return UsersDto.of(user);
    }

    public UsersDto reissuance(TokenDto userToken) {
        String secretKey = new BigInteger(130, new SecureRandom()).toString(32);
        Users user  = tokenConverter.getUser(userToken);
        UsersDto usersDto = UsersDto.builder().secretKey(secretKey).build();
        usersRepository.updateUser(user, usersDto);
        return UsersDto.of(user);
    }

    public void updateUser(TokenDto userToken, UsersDto usersDto) {
        Users foundUser  = tokenConverter.getUser(userToken);
        usersRepository.updateUser(foundUser, usersDto);
    }

    public void deleteUser(TokenDto userToken) {
        Users foundUser  = tokenConverter.getUser(userToken);
        usersRepository.deleteUser(foundUser);
    }
}
