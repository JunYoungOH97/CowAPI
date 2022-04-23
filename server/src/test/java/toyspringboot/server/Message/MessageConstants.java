package toyspringboot.server.Message;

public class MessageConstants {
    // base path
    public static final String POST_MESSAGE_PATH = "/messages";
    public static final String GET_MESSAGE_PATH = "/messages/{id}";
    public static final String PUT_MESSAGE_PATH = "/messages/{id}";
    public static final String DELETE_MESSAGE_PATH = "messages/{id}";

    // values
    public static final String MESSAGE_CONTENT = "hello";
    public static final Long MESSAGE_ID = 1L;

    // response message
    public static final String POST_SUCCEED = "메시지 생성 성공";
    public static final String GET_SUCCEED = "메시지 조회 성공";
    public static final String PUT_SUCCEED = "메시지 수정 성공";
    public static final String DELETE_SUCCEED = "메시지 삭제 성공";
}
