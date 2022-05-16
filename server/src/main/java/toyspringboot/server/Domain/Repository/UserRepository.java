package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User SET nickname = :nickname WHERE id = :id", nativeQuery = true)
    int updateNickname(@Param("nickname") String nickname, @Param("id") Long userId);
}
