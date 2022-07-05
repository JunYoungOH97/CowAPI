package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Domain.Dto.QnaDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Qna;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
    @Query(value =  "Select *" +
                    "From Qna q " +
                    "Where q.isDeleted != TRUE " +
                    "Order by q.updatedAt DESC, q.id ASC " +
                    "Limit :pageId, :pageCnt"
            , nativeQuery = true)
    List<Qna> findByPage(@Param("pageId") Long pageId, @Param("pageCnt") Long pageCnt);

    @Query(value =  "Select Count(*) " +
            "From Qna q " +
            "Where q.isDeleted != TRUE"
            , nativeQuery = true)
    Long countById();

    default void updateQna(Qna qna, QnaDto qnaDto) {
        if(qnaDto.getTitle() != null) qna.setTitle(qnaDto.getTitle());
        if(qnaDto.getContent() != null) qna.setContent(qnaDto.getContent());
        if(qnaDto.getIsDeleted() != null) qna.setIsDeleted(qnaDto.getIsDeleted());
        qna.setUpdater("API");

        if(qnaDto.getCreatedAt() != null) qna.setCreatedAt(qnaDto.getCreatedAt());
        qna.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if(qnaDto.getDeletedAt() != null) qna.setDeletedAt(qnaDto.getDeletedAt());

        if(qnaDto.getUserDto() != null) qna.setUser(UsersDto.toEntity(qnaDto.getUserDto()));
    }

    default void deleteQna(Qna qna) {
        qna.setIsDeleted(true);
        qna.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        qna.setUpdater("API");
    }
}
