package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Entity.Messages;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
}
