package toyspringboot.server.Domain.Repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    default User deleteByEmail(Object userDto) {
        if(!(userDto instanceof UserDto)) {
            throw new ResponseStatusException(BAD_REQUEST, "회원정보 삭제에 잘못된 정보 입니다.");
        }
        User user = findByEmail(((UserDto) userDto).getEmail()).orElseThrow();
        user.deleteUser(((UserDto) userDto));
        return user;
    }
}
