package server.server.Service;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class DateConverterComponent {
    public DateConverterComponent() { }

    public String DateToResponse(Timestamp date) {
        String[] str = date.toString().split(" ");
        String f = str[0].replace("-", ". ");
        String s = str[1].substring(0, str[1].length() - 2);

        int hour = Integer.parseInt(s.substring(0, 2));
        String prefix = (hour < 12) ? " AM " : " PM ";
        hour = hour % 12;

        String h = (Integer.toString(hour).length() > 1) ? Integer.toString(hour) : "0" + Integer.toString(hour);

        return f + prefix + h + s.substring(2, s.length());
    }
}
