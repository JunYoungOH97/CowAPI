package toyspringboot.server.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.Notice;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    default void updateNotice(Notice notice, NoticeDto noticeDto) {
        if(noticeDto.getTitle() != null) notice.setTitle(noticeDto.getTitle());
        if(noticeDto.getContent() != null) notice.setContent(noticeDto.getContent());
        if(noticeDto.getCreatedDate() != null) notice.setCreatedDate(noticeDto.getCreatedDate());
        if(noticeDto.getCreator() != null) notice.setCreator(noticeDto.getCreator());
        if(noticeDto.getUserDto() != null) notice.setUser(UserDto.toEntity(noticeDto.getUserDto()));
        if(noticeDto.getUpdater() != null) notice.setUpdater(noticeDto.getUpdater());

        notice.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    default void deleteNotice(Notice notice) {
        notice.setIsDeleted(true);
        notice.setUpdater("API");
        notice.setDeletedDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}
