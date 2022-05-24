package toyspringboot.server.Notice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class NoticeTestConstants {
    // ---------- API ----------
    public static final String Notice_create_API = "/notices/notice";
    public static final String Notice_read_API = "/notices/1";
    public static final String Notice_update_API = "/notices/notice";
    public static final String Notice_delete_API = "/notices/notice";

    // ---------- test information ----------
    // 존재하지 않는 QnA
    public static final Long Test_Notice_id = 2L;
    public static final String Test_Notice_user = "JunYoung@naver.com";
    public static final String Test_Notice_title = "테스트 입니다.";
    public static final String Test_Notice_content = "test";
    public static final Boolean Test_Notice_isDeleted = true;
    public static final Timestamp Test_Notice_createdDate = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    public static final Timestamp Test_Notice_updatedDate = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    public static final Timestamp Test_Notice_deletedDate = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    public static final String Test_Notice_creator = "Test code";
    public static final String Test_Notice_updater = "Test code";

    // 존재하는 QnA
    public static final Long Exist_Notice_id = 1L;
    public static final String Exist_Notice_user = "user@junyoung.com";
    public static final String Exist_Notice_title = "title";
    public static final String Exist_Notice_content = "no content";
    public static final Boolean Exist_Notice_isDeleted = false;
    public static final Timestamp Exist_Notice_createdDate = Timestamp.valueOf("2022-05-24 19:32:35");
    public static final Timestamp Exist_Notice_updatedDate = Timestamp.valueOf("2022-05-24 19:32:35");
    public static final Timestamp Exist_Notice_deletedDate = null;
    public static final String Exist_Notice_creator = "Directly";
    public static final String Exist_Notice_updater = "Directly";




    // ---------- response message ----------
}
