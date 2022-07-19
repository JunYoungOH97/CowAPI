package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.server.Domain.Entity.AiInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface AiInfoRepository extends JpaRepository<AiInfo, String> {
    @Query(value =  "Select * " +
            "From AiInfo a " +
            "Where a.isDeleted != True " +
            "Order by a.name ASC "
            , nativeQuery = true)
    Optional<List<AiInfo>> findAiByExist();
}
