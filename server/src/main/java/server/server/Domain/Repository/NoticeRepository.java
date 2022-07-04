package server.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.server.Domain.Dto.NoticeDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Notice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value =  "Select * " +
                    "From Notice n " +
                    "Where n.isDeleted != TRUE " +
                    "Order by n.updatedAt DESC, n.id ASC " +
                    "Limit :pageId, :pageCnt"
            , nativeQuery = true)
    List<Notice> findByPage(@Param("pageId") Long pageId, @Param("pageCnt") Long pageCnt);

    default void updateNotice(Notice notice, NoticeDto noticeDto) {
        if(noticeDto.getTitle() != null) notice.setTitle(noticeDto.getTitle());
        if(noticeDto.getContent() != null) notice.setContent(noticeDto.getContent());
        if(noticeDto.getUserDto() != null) notice.setUser(UsersDto.toEntity(noticeDto.getUserDto()));
        if(noticeDto.getUpdater() != null) notice.setUpdater(noticeDto.getUpdater());

        notice.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    default void deleteNotice(Notice notice) {
        notice.setIsDeleted(true);
        notice.setUpdater("API");
        notice.setDeletedAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }
}
