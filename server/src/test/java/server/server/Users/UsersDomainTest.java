package server.server.Users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static server.server.Users.UsersTestConstants.*;

@DataJpaTest
public class UsersDomainTest {
    @Autowired
    private UsersRepository usersRepository;
    private static Users user;

    @BeforeAll
    static void setUser() {
        user = Users.builder()
                .email(User_email)
                .password(User_password)
                .secretKey(User_secretKey)
                .isAdmin(User_admin)
                .isDeleted(User_deleted)
                .createdAt(User_CreateAt)
                .updater(User_updater)
                .build();
    }

    @Test
    @DisplayName("[Domain] 사용자 생성 테스트")
    public void create() {
        // given
        // when
        Users saveUser = usersRepository.save(user);

        // then
        assertEquals(User_email, saveUser.getEmail());
    }

    @Test
    @DisplayName("[Domain] 사용자 조회 테스트")
    public void read() {
        // given
        usersRepository.save(user);

        // when
        Users foundUser = usersRepository.findByEmail(User_email).orElseThrow();

        // then
        assertEquals(User_email, foundUser.getEmail());
    }

    @Test
    @DisplayName("[Domain] 사용자 수정 테스트")
    public void update() {
        // given
        usersRepository.save(user);

        UsersDto updateInfo = UsersDto.of(user);
        updateInfo.setPassword("update");

        // when
        usersRepository.updateUser(user, updateInfo);

        // then
        assertEquals(updateInfo.getPassword(), user.getPassword());
    }

    @Test
    @DisplayName("[Domain] 사용자 삭제 테스트")
    public void delete() {
        // given
        usersRepository.save(user);

        // when
        usersRepository.deleteUser(user);

        // then
        assertEquals(true, user.getIsDeleted());
    }
}
