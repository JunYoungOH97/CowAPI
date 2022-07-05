package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.server.Domain.Entity.Ai;
import server.server.Domain.Entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface AiPageRepository extends JpaRepository<Ai, String> {
    Optional<Ai> findByName(String name);

    @Query(value =  "Select * " +
                    "From Ai a " +
                    "Where a.isDeleted != True " +
                    "Order by a.updatedAt DESC, a.name ASC "
            , nativeQuery = true)
    Optional<List<Ai>> findAiByExist();
}
