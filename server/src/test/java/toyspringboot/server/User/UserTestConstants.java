package toyspringboot.server.User;

public class UserTestConstants {
    // ---------- API ----------
    public static final String SignIn_API = "/users/signin";
    public static final String SignUp_API = "/users/signup";
    public static final String UpdateUser_API = "/users/user";
    public static final String DeleteUser_API = "/users/user";

    // ---------- test information ----------
    // 존재하지 않는 유저
    public static final String User_email = "testEmail";
    public static final String User_password = "testPassword";
    public static final Boolean User_admin = false;
    public static final Boolean User_IsDeleted = true;


    // 존재하는 유저
    public static final String Exist_User_email = "Admin";
    public static final String Exist_User_password = "a123";
    public static final Boolean Exist_User_admin = true;
    public static final Boolean Exist_User_isDeleted = false;


    // ---------- response message ----------
}
