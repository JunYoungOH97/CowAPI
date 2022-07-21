package server.server.Users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static server.server.Users.UsersTestConstants.*;

@DataJpaTest
@Transactional
public class UsersDomainTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("[Domain] 사용자 조회 테스트")
    public void readTest() {
        // given
        // when
        Users user = usersRepository.findByEmail(Exist_User_email).orElseThrow();

        // then
        assertEquals(Exist_User_email, user.getEmail());
    }

}
