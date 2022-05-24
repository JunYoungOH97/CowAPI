package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Entity.Notice;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
