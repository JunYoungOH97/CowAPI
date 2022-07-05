package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResponseDto {
    String name;
    Double responseTime;
    Double accuracy;
    String requestURI ;
    String method;
    Object req;
    Object res;
    String field;

    Long totalUser;
    Long todayUser;
    Timestamp updatedAt;
}
