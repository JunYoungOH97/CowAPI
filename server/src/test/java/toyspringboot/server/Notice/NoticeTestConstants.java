package toyspringboot.server.Notice;

public class NoticeTestConstants {
    // ---------- API ----------
    public static final String Notice_create_API = "/notices/notice";
    public static final String Notice_read_API = "/notices/1";
    public static final String Notice_update_API = "/notices/notice";
    public static final String Notice_delete_API = "/notices/notice";

    // ---------- test information ----------
    // 존재하지 않는 QnA
    public static final Long Test_Notice_id = 2L;
    public static final String Test_Notice_user = "test@junyoung.com";
    public static final String Test_Notice_title = "테스트 입니다.";
    public static final String Test_Notice_content = "test";
    public static final Boolean Test_Notice_isDeleted = true;

    // 존재하는 QnA
    public static final Long Exist_Notice_id = 1L;
    public static final String Exist_Notice_user = "user@junyoung.com";
    public static final String Exist_Notice_title = "title";
    public static final String Exist_Notice_content = "no content";
    public static final Boolean Exist_Notice_isDeleted = false;


    // ---------- response message ----------
}
