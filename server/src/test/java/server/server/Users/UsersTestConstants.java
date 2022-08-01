package server.server.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsersTestConstants {
    // ---------- API ----------
    public static final String SignIn_API = "/users/user";
    public static final String SignUp_API = "/signup";
    public static final String UpdateUser_API = "/users/user";
    public static final String DeleteUser_API = "/users/user";

    // ---------- test information ----------
    public static final String User_email = "test";
    public static final String User_password = "t123";
    public static final String User_secretKey = "secret";
    public static final Boolean User_deleted = false;
    public static final Boolean User_admin = true;
    public static final Timestamp User_CreateAt = Timestamp.valueOf(LocalDateTime.now());
    public static final String User_updater = "test";

    public static final String User_accessToken = "test";
    public static final Long User_expire = 30 * 10L;
    public static final String User_grantType = "Bearer";
    // ---------- response message ----------
}
