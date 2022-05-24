package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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


    default void updateQnA(QnA qnA, QnADto qnADto) {
        if(qnADto.getTitle() != null) qnA.setTitle(qnADto.getTitle());
        if(qnADto.getContent() != null) qnA.setContent(qnADto.getContent());
        if(qnADto.getCreatedDate() != null) qnA.setCreatedDate(qnADto.getCreatedDate());
        if(qnADto.getDeletedDate() != null) qnA.setDeletedDate(qnADto.getDeletedDate());
        if(qnADto.getCreator() != null) qnA.setCreator(qnADto.getCreator());
        if(qnADto.getUserDto() != null) qnA.setUser(UserDto.toEntity(qnADto.getUserDto()));

        qnA.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        qnA.setUpdater("API");
    }

    default void deleteQnA(QnA qnA) {
        qnA.setIsDeleted(true);
        qnA.setDeletedDate(Timestamp.valueOf(LocalDateTime.now()));
        qnA.setUpdater("API");
    }
}
