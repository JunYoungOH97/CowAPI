package toyspringboot.server.User;

public class UserTestConstants {
    // API
    public static final String SignIn_API = "/users/user";
    public static final String SignUp_API = "/users/newUser";

    // test values
    public static final Long User_id = 1L;
    public static final String User_email = "testEmail@junyoung.com";
    public static final String User_password = "testPassword";
    public static final String User_nickname= "testNickname";
    public static final boolean User_admin = true;

    // response message
    public static final String POST_SUCCEED = "유저 생성 성공";
    public static final String GET_SUCCEED = "유저 조회 성공";
    public static final String PUT_SUCCEED = "유저 수정 성공";
    public static final String DELETE_SUCCEED = "유저 삭제 성공";
}
