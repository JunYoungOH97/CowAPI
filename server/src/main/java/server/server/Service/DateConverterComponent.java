package server.server.Service;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;


@Component
public class DateConverterComponent {
    public DateConverterComponent() { }

    public String DateToResponse(Timestamp date) {
        String s = date.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy. MM. dd a hh:mm:ss"));
        s = s.replace("오후", "PM");
        s = s.replace("오전", "AM");
        return s;
    }
}
