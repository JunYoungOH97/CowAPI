package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.User.UserTestConstants.*;

@SpringBootTest
@Transactional
public class UserDomainTest {
    @Autowired
    private UserRepository userRepository;

    private final DomainTest domainTest = new DomainTest();

    @Test
    @DisplayName("[Domain] 사용자 회원가입 테스트")
    public void createTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        User user = User.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build();

        // when
        User newUser = domainTest.createTest(user, userRepository, "save");

        // then
        assertEquals(user.getEmail(), newUser.getEmail());
    }


    @Test
    @DisplayName("[Domain] 사용자 조회 테스트")
    public void readTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        // when
        User foundUser = domainTest.readTest(User_id, userRepository, "findById");

        // then
        assertEquals(User_id, foundUser.getId());
    }


    @Test
    @DisplayName("[Domain] 사용자 수정 테스트")
    public void updateTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        // when
        User modifiedUser = domainTest.updateTest(User_id, User.class, userRepository, "findById", User_nickname, "save", "setNickname");

        // then
        assertEquals(User_nickname, modifiedUser.getNickname());
    }

    @Test
    @DisplayName("[Domain] 사용자 삭제 테스트")
    public void deleteTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        User user = User.builder()
                .id(User_id)
                .build();

        // when
        domainTest.deleteTest(user, userRepository, "delete");

        // then
        try {
            domainTest.readTest(User_id, userRepository, "findById");
            fail();

        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

}
