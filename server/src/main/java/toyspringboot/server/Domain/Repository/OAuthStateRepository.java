package toyspringboot.server.Domain.Repository;

import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.OAuthState;

public interface OAuthStateRepository extends JpaRepository<OAuthState, String> {
}
