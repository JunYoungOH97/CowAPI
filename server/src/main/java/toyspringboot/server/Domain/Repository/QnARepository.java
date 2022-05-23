package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Entity.QnA;

import java.util.List;
import java.util.Optional;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
    @Query(value =
            "Select * From QnA q Where (q.title Like %:query% Or q.content Like %:query%) " +
            "And q.isDeleted != TRUE " +
            "Limit :searchCnt", nativeQuery = true)
    List<QnA> searchByQuery(@Param("query") String query, @Param("searchCnt") Long searchCnt);


    @Query(value =
            "Select * From QnA q Where q.isDeleted != TRUE " +
            "Order by q.updatedDate DESC, q.id ASC " +
            "Limit :pageId, :pageCnt", nativeQuery = true)
    List<QnA> findByPage(@Param("pageId") Long pageId, @Param("pageCnt") Long pageCnt);
}
