package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .nickname(User_nickname)
                .admin(User_admin)
                .build();

        // when
        User newUser = (User) test(user, userRepository, "save");

        // then
        assertEquals(user.getEmail(), newUser.getEmail());
    }




    
}
