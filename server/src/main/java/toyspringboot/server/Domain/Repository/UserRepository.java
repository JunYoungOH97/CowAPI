package toyspringboot.server.Domain.Repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(Object email);
    Optional<User> findByEmail(Object email);

    default void updateUser(User user, UserDto userDto) {
        if(userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if(userDto.getAdmin() != null) user.setAdmin(userDto.getAdmin());
        if(userDto.getIsDeleted() != null) user.setIsDeleted(userDto.getIsDeleted());
        if(userDto.getCreatedDate() != null) user.setCreatedDate(userDto.getCreatedDate());
        if(userDto.getUpdatedDate() != null) user.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        if(userDto.getDeletedDate() != null) user.setDeletedDate(userDto.getDeletedDate());
        if(userDto.getCreator() != null) user.setCreator(userDto.getCreator());
        if(userDto.getUpdater() != null) user.setUpdater("API");

        user.setAdmin(true);
    }

    default void deleteUser(User user) {
        if(user.getIsDeleted().equals(false)) {
            user.setIsDeleted(true);
            user.setDeletedDate(Timestamp.valueOf(LocalDateTime.now()));
            user.setUpdater("API");
        }
        else {
            throw new ResponseStatusException(BAD_REQUEST, "이미 삭제된 유저 입니다.");
        }
    }
}
