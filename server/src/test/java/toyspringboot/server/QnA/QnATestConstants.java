package toyspringboot.server.QnA;

import toyspringboot.server.DefaultTestConstants;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class QnATestConstants extends DefaultTestConstants {
    // ---------- API ----------
    public static final String QnA_create_API = "/QnAs/QnA";
    public static final String QnA_read_API = "/QnAs/1";
    public static final String QnA_update_API = "/QnAs/QnA";
    public static final String QnA_delete_API = "/QnAs/QnA";

    public static final String QnA_Page_API = "/QnAs/page/1";
    public static final String QnA_Query_API = "/QnAs/공지";

    // ---------- test information ----------
    public static final String QnA_search_query = "공지";
    public static final Long QnA_search_cnt = 5L;

    public static final Long QnA_Page = 1L;
    public static final Long QnA_Page_cnt = 5L;

    // 존재하지 않는 QnA
    public static final Long QnA_id = 2L;
    public static final String QnA_user = "test@junyoung.com";
    public static final String QnA_title = "테스트 입니다.";
    public static final Boolean QnA_isDeleted = true;
    public static final Integer QnA_Index = 1;
    public static final String QnA_content = "test content";

    // 존재하는 QnA
    public static final Long Exist_QnA_id = 1L;
    public static final String Exist_QnA_user = "user@junyoung.com";
    public static final String Exist_QnA_title = "공지글 입니다.";
    public static final Boolean Exist_QnA_isDeleted = false;
    public static final Integer Exist_QnA_Index = 0;


    // ---------- response message ----------
}
