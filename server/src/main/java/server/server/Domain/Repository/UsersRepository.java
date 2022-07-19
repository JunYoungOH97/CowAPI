package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    boolean existsByEmail(Object email);
    Optional<Users> findByEmail(Object email);

    default void updateUser(Users user, UsersDto userDto) {
        if(userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if(userDto.getSecretKey() != null) user.setSecretKey(userDto.getSecretKey());

        if(userDto.getIsAdmin() != null) user.setIsAdmin(userDto.getIsAdmin());
        if(userDto.getIsDeleted() != null) user.setIsDeleted(userDto.getIsDeleted());
        if(userDto.getUpdater() != null) user.setUpdater("API");

        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if(userDto.getCreatedAt() != null) user.setCreatedAt(userDto.getCreatedAt());
        if(userDto.getUpdatedAt() != null) user.setUpdatedAt(now);
        if(userDto.getDeletedAt() != null) user.setDeletedAt(userDto.getDeletedAt());
    }

    default void deleteUser(Users user) {
        if(user.getIsDeleted().equals(false)) {
            user.setIsDeleted(true);

            Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            user.setDeletedAt(now);

            user.setUpdater("API");
        }
        else {
            throw new ResponseStatusException(BAD_REQUEST, "이미 삭제된 유저 입니다.");
        }
    }

}
