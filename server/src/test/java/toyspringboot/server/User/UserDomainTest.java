package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestConfig.DomainTest;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.User.UserTestConstants.*;

public class UserDomainTest extends DomainTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("[Domain] 사용자 생성 테스트")
    public void createTest() {
        // given
        User user = User.builder()
                .email(User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(false)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Create_Date)
                .updater(Creator_Member)
                .build();

        // when
        User newUser = userRepository.save(user);

        // then
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test
    @DisplayName("[Domain] 사용자 조회 테스트")
    public void readTest() {
        // given
        // when
        User user = userRepository.findByEmail(Exist_User_email).orElseThrow();

        // then
        assertEquals(Exist_User_email, user.getEmail());
    }

    @Test
    @DisplayName("[Domain] 사용자 수정 테스트")
    public void updateTest() {
        // given
        User user = userRepository.findByEmail(Exist_User_email).orElseThrow();

        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .build();

        // when
        userRepository.updateUser(user, userDto);

        // then
        assertEquals(user.getPassword(), User_password);
    }

    @Test
    @DisplayName("[Domain] 사용자 삭제 테스트")
    public void deleteTest() {
        // given
        User user = userRepository.findByEmail(Exist_User_email).orElseThrow();

        String userToken = "testToken";

        // when
        userRepository.deleteUser(user);

        // then
        assertEquals(user.getIsDeleted(), User_IsDeleted);
    }
}
