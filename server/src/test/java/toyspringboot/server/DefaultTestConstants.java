package toyspringboot.server;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DefaultTestConstants {
    public static final Timestamp Create_Date = Timestamp.valueOf(LocalDateTime.now());
    public static final String Creator_Member = "Test Code";

    public static final Timestamp Update_Date = Timestamp.valueOf(LocalDateTime.now());
    public static final String Update_Member = "Test Code";
}
