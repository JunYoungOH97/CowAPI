package toyspringboot.server.User;

public class UserTestConstants {
    // ---------- API ----------
    public static final String SignIn_API = "/users/user";
    public static final String SignUp_API = "/users/newUser";

    // ---------- test information ----------

    // false case
//    public static final Long User_id = 2L;
//    public static final String User_email = "testEmail@junyoung.com";
//    public static final String User_password = "testPassword";
//    public static final String User_nickname= "testNickname";
//    public static final boolean User_admin = true;

    // true case
    public static final Long User_id = 1L;
    public static final String User_email = "user@junyoung.com";
    public static final String User_password = "password123";
    public static final String User_nickname= "JunYoung";
    public static final boolean User_admin = false;


    // ---------- response message ----------
}
