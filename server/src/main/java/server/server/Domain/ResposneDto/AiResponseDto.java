package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResponseDto {
    Long todayUser;
    Long totalUser;
    String name;
    Double responseTime;
    Double accuracy;
    Timestamp updatedAt;

    String requestURI ;
    String method;
    String req;
    String res;
}
