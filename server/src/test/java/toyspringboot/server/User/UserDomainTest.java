package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.User.UserTestConstants.*;

public class UserDomainTest extends DomainTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("[Domain] 사용자 생성 테스트")
    public void createTest() throws Exception {
        // given
        User user = User.builder()
                .email(User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(false)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .build();

        // when
        User newUser = (User) test(user, userRepository, "save");

        // then
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test
    @DisplayName("[Domain] 사용자 조회 테스트")
    public void readTest() throws Exception {
        // given
        // when
        Optional<User> foundUser = (Optional<User>) test(Exist_User_email, userRepository, "findByEmail");

        // then
        assertTrue(foundUser.isPresent());
        foundUser.ifPresent(user -> assertEquals(Exist_User_email, user.getEmail()));
    }

    @Test
    @DisplayName("[Domain] 사용자 수정 테스트")
    public void updateTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .build();

        // when
        User updatedUser = (User) test(userDto, userRepository, "updateByEmail");

        // then
        assertEquals(updatedUser.getPassword(), User_password);
    }

    @Test
    @DisplayName("[Domain] 사용자 삭제 테스트")
    public void deleteTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .isDeleted(User_IsDeleted)
                .build();

        String userToken = "testToken";

        // when
        User deletedUser = (User) test(userDto, userRepository, "deleteByEmail");

        // then
        assertEquals(deletedUser.getIsDeleted(), User_IsDeleted);
    }
}
